package com.uth.fms.common.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {
    @Value("${app.jwt.secret}")
    private String jwtSecret;
    @Value("${app.jwt.access-expiration}")
    private Long jwtAccessTime;
    @Value("${app.jwt.refresh-expiration}")
    private Long jwtRefreshTime;

    // Tạo key để ký xác thực JWT
    private SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /*
     * Giải mã token để lấy Claims
     */
    private Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                   .verifyWith(getSigningKey())
                   .build()
                   .parseSignedClaims(token)
                   .getPayload();
    }

    /**
     * Lấy id_user từ JWT
     * @param token:string
     */
    public Long getUserIdFromToken(String token) {
        return Long.valueOf(getClaimsFromToken(token).getSubject());
    }

    /**
    * Lấy username từ JWT
    * @param token:string
    */
    public String getUsernameFromToken(String token) {
        return getClaimsFromToken(token).get("username").toString();
    }

    /**
     * Lấy role từ JWT
     * @param token:string
     */
    public String getUserRolesFromToken(String token) {
        return getClaimsFromToken(token).get("roles").toString();
    }

    /**
     * Tạo JWT Access
     * @param authentication
     */
    public String generateAccessToken(Authentication authentication) {
        CustomeUserDetail userPrincipal = (CustomeUserDetail) authentication.getPrincipal();
        String username = userPrincipal.getUsername();

        //Lấy danh sách role
        String roles = userPrincipal.getAuthorities()
                                    .stream()
                                    .map(GrantedAuthority::getAuthority)//Chuyển object sang string
                                    .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtAccessTime);

        return Jwts.builder()
                   .subject(String.valueOf(userPrincipal.getId()))
                   .claim("username", username)
                   .claim("roles", roles)
                   .issuedAt(now)
                   .expiration(expiration)
                   .signWith(getSigningKey())
                   .compact();
    }

    /**
     * Tạo JWT RefreshToken
     * @param authentication
     */
    public String generateRefreshToken(Authentication authentication) {
        CustomeUserDetail userPrincipal = (CustomeUserDetail) authentication.getPrincipal();
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtRefreshTime);

        return Jwts.builder()
                   .subject(String.valueOf(userPrincipal.getId()))
                   .issuedAt(now)
                   .expiration(expiration)
                   .signWith(getSigningKey())
                   .compact();
    }

    /*
     * Giải mã JWT, kiểm tra token đúng với chữ kí không ?
     */
    public Boolean validateToken(String token) {
        try {
            Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (MalformedJwtException ex) {
            // Token bị sai cấu trúc (không đúng định dạng JWT, bị sửa đổi, thiếu phần...)
            log.error("Invalid JWT token: {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            // Token đã hết hạn sử dụng (thời gian hiện tại > expiration)
            log.error("Expired JWT token: {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            // Token có định dạng hoặc thuật toán không được hỗ trợ (ví dụ dùng thuật toán lạ)
            log.error("Unsupported JWT token: {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            // Chuỗi token rỗng hoặc không chứa claims (token null hoặc chỉ toàn khoảng trắng)
            log.error("JWT claims string is empty: {}", ex.getMessage());
        }
        return false;
    }
}
