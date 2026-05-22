package com.uth.fms.user.services.impl;


import com.uth.fms.common.exception.BusinessException;
import com.uth.fms.common.security.CustomUserDetailService;
import com.uth.fms.common.security.CustomeUserDetail;
import com.uth.fms.user.entity.User;
import com.uth.fms.user.repositories.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CustomeUserDetailService implements CustomUserDetailService {
    final UserRepository userRepository;

    /*
    * Tạo userDetail
    */
    private CustomeUserDetail buildPrincipal(User user) {
        //Kiểm tra tài khoản có đang bị khóa hoặc bị xóa mềm không
        if (!user.getEnabled()){
            throw new BusinessException(403, "ACCOUNT_LOCKED", "Tài khoản hiện tại đang bị vô hiệu hóa");
        }
        // Tạo đối tượng GrantedAuthority từ role của user
        // Vì Spring Security thường yêu cầu authority có tiền tố "ROLE_" (nếu dùng hasRole)
        // Lấy role từ enum và chuyển thành tên, thêm tiền tố "ROLE_"
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRoles().name());
        return CustomeUserDetail.builder()
                            .id(user.getId())
                            .username(user.getUsername())
                            .password(user.getPassword())
                            .authorities(Collections.singletonList(authority))
                            .build();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException("Không tìm thấy user"));
        //Kiểm tra tài khỏan có đang bị khóa hoặc xóa mềm không
        if (!user.getEnabled()) {
            throw new BusinessException(403, "FORBIDDEN", "Tài khoản đã bị khóa");
        }
        return buildPrincipal(user);
    }

    @Override
    public UserDetails loadUserByUserId (Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(401,"UNAUTHORIZED", "Người dùng không tồn tại"));
        //Kiểm tra tài khỏan có đang bị khóa hoặc xóa mềm không
        if (!user.getEnabled()) {
            throw new BusinessException(403, "FORBIDDEN", "Tài khoản đã bị khóa");
        }
        return buildPrincipal(user);
    }
}
