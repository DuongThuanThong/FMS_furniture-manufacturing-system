package com.uth.fms.common.security;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailService extends UserDetailsService {
    UserDetails loadUserByUserId(Long id);
}
