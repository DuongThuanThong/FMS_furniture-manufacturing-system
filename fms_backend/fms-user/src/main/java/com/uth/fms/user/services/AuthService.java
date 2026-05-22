package com.uth.fms.user.services;

import com.uth.fms.user.dto.reponse.LoginResponse;
import com.uth.fms.user.dto.request.LoginRequest;
import com.uth.fms.user.dto.request.RegisterRequest;

public interface AuthService {
    
    LoginResponse login(LoginRequest request);
    String register(RegisterRequest request);

    /**
     * Làm mới accessToken đã hết hạn bằng refreshToken
     * @param refreshToken
     * @return 1 accessToken dạng String
     */
    String refreshAccessToken(String refreshToken);

}
