package com.uth.fms.user.services.impl;

import com.uth.fms.common.exception.BusinessException;
import com.uth.fms.common.security.CustomUserDetailService;
import com.uth.fms.common.security.CustomeUserDetail;
import com.uth.fms.common.security.JwtTokenProvider;
import com.uth.fms.user.dto.reponse.LoginResponse;
import com.uth.fms.user.dto.request.LoginRequest;
import com.uth.fms.user.dto.request.RegisterRequest;
import com.uth.fms.user.entity.User;
import com.uth.fms.user.mapper.UserMapping;
import com.uth.fms.user.repositories.UserRepository;
import com.uth.fms.user.services.AuthService;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    UserRepository userRepository;
    JwtTokenProvider jwtTokenProvider;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    CustomUserDetailService customUserDetailService;
    UserMapping userMapping;

    @Override
    public LoginResponse login(LoginRequest request) {
        //Xác thực người dùng
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        //Đưa vào SecurityContext mục đích lưu Authentication vào bộ nhớ Ram cho đến hết request
        SecurityContextHolder.getContext()
                             .setAuthentication(authentication);

        CustomeUserDetail userDetail = (CustomeUserDetail) authentication.getPrincipal();
        User user = userRepository.findById(userDetail.getId())
                                  .orElseThrow(() -> new BusinessException(404, "USER_NOT_FOUND", "Người dùng không có tồn tại!"));

        //Tạo 2 token :accessToken và refreshToken
        String accessToken = jwtTokenProvider.generateAccessToken(authentication);
        String refreshToken = jwtTokenProvider.generateRefreshToken(authentication);

        //Mapping dữ liệu User(MODEL) sang thành LoginResponse
        LoginResponse response = userMapping.toLoginResponse(user);

        //Gắn thêm 2 cái token
        return response.toBuilder()
                       .accessToken(accessToken)
                       .refreshToken(refreshToken)
                       .build();
    }

    @Override
    public String register(RegisterRequest request) {

        //Kiểm tra từng fiel (Username, phone and email)
        List<String> validationErrors = new ArrayList<>();
        if (userRepository.existsByUsername(request.getUsername())) {
            validationErrors.add("Username đã tồn tại");
        }

        if (request.getEmail() != null && userRepository.existsByEmail(request.getEmail())) {
            validationErrors.add("Email đã tồn tại");
        }

        if (request.getPhoneNumber() != null && userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            validationErrors.add("SĐT đã tồn tại");
        }

        //Nếu List chứa Error không rỗng
        if (!validationErrors.isEmpty()) {
            // Cách nhanh nhất: Nối các lỗi lại bằng dấu phẩy |
            String combinedErrors = String.join(" | ", validationErrors);
            throw new BusinessException(400, "VALIDATION_FAILED", combinedErrors);
        }

        //Chuyển Request sang thành Entity
        User user = userMapping.toEntity(request);
        //Set passwor đã mã hóa
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        //Lưu user
        userRepository.save(user);
        return "User" + user.getUsername() + "đã đăng kí thành công!";
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        //Kiểm refreshToken còn hạn không
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new BusinessException(401, "TOKEN_IS_EXPIRED", "Token đã hết hạn sử dụng");
        }
        //Lấy userId từ refreshToken
        Long userId = jwtTokenProvider.getUserIdFromToken(refreshToken);
        //Check user có tồn tại k
        UserDetails userDetails = customUserDetailService.loadUserByUserId(userId);

        //Tạo lại 1 phiếu Authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        //Return 1 accesstoken mới
        return jwtTokenProvider.generateAccessToken(authentication);
    }
}
