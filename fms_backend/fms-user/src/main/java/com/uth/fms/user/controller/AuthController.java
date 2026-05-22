package com.uth.fms.user.controller;

import com.uth.fms.user.dto.response.LoginResponse;
import com.uth.fms.user.dto.request.LoginRequest;
import com.uth.fms.user.dto.request.RegisterRequest;
import com.uth.fms.user.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    @Value("${app.jwt.access-expiration}")
    private Long jwtAccessTime;
    @Value("${app.jwt.refresh-expiration}")
    private Long jwtRefreshTime;
    private final AuthService authService;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            String result = authService.register(request);
            return ResponseEntity.ok().body(Map.of(
                    "success", true,
                    "message", result
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of(
                    "success", false,
                    "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {
        LoginResponse loginResponse = authService.login(request);

        // Tạo Cookie cho Access Token
        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", loginResponse.getAccessToken())
                .httpOnly(true)
                .secure(false) // Đặt true nếu dùng HTTPS trên production
                .path("/")
                .maxAge(jwtAccessTime)
                .sameSite("Lax")
                .build();

        // Tạo Cookie cho Refresh Token
        ResponseCookie refreshTokenCookie = ResponseCookie.from("refreshToken", loginResponse.getRefreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(jwtRefreshTime)
                .sameSite("Lax")
                .build();

        // Xóa token khỏi body để bảo mật hoàn toàn
        LoginResponse safeResponse = loginResponse.toBuilder()
                .accessToken(null)
                .refreshToken(null)
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .header(HttpHeaders.SET_COOKIE, refreshTokenCookie.toString())
                .body(safeResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@CookieValue(name = "refreshToken", required = false) String refreshToken, HttpServletResponse response) {
        if (refreshToken == null || refreshToken.isEmpty()) {
            return ResponseEntity.status(401).body("Refresh token không tồn tại trong cookie");
        }

        String newAccessToken = authService.refreshAccessToken(refreshToken);

        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", newAccessToken)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(jwtAccessTime)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, accessTokenCookie.toString())
                .body("Đã cấp lại Access Token mới");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        ResponseCookie clearAccessToken = ResponseCookie.from("accessToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        ResponseCookie clearRefreshToken = ResponseCookie.from("refreshToken", "")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, clearAccessToken.toString())
                .header(HttpHeaders.SET_COOKIE, clearRefreshToken.toString())
                .body("Đăng xuất thành công");
    }


}
