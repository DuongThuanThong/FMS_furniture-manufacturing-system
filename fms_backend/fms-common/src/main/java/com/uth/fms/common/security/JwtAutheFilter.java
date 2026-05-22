package com.uth.fms.common.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtAutheFilter extends OncePerRequestFilter {
    final JwtTokenProvider jwtTokenProvider;
    final CustomUserDetailService customUserDetailService;
    /**
     * Trích xuất JWT từ header Authorization.
     * Header có dạng: Authorization: Bearer <token>
     */
    private String getJwtTokenFromRequest(HttpServletRequest request) {
        // Ưu tiên đọc từ Cookie
        if (request.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : request.getCookies()) {
                if ("accessToken".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }

        // Fallback đọc từ Header Authorization
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // cắt bỏ "Bearer "
        }
        return null;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJwtTokenFromRequest(request);

            //Kiểm tra token tồn tại và token hợp lệ k
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                // Lấy userId từ Subject của token
                Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
                //Load user từ db
                UserDetails userDetails = customUserDetailService.loadUserByUserId(userId);

                if (userDetails != null) {
                    //Tạo đối tượng Authentication
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (Exception ex) {
            log.error("Failed to set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }
}
