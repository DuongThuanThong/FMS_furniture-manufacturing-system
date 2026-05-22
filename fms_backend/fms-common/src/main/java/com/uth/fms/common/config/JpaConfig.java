package com.uth.fms.common.config;

import com.uth.fms.common.security.CustomeUserDetail;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration //Tạo bean
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")

//Dòng này kích hoạt tính năng JPA Auditing.
//
//Nó quét các Entity của bạn (như User, Order, Material,....) extend BasseEntity
//
//Nếu Entity đó có các trường được đánh dấu @CreatedBy hoặc @LastModifiedBy, JPA sẽ tự động điền dữ liệu vào.
//auditorAwareRef = "auditorProvider": Nó ra lệnh cho Spring:
// "Khi cần biết ID người dùng để điền vào cột created_by, hãy gọi cái Bean tên là auditorProvider ở bên dưới".
public class JpaConfig {
    @Bean
    public AuditorAware<Long> auditorProvider() {
        return () -> {
            // Lấy thông tin xác thực từ SecurityContext
            Authentication authentication = SecurityContextHolder.getContext()
                                                                 .getAuthentication();

            // Nếu chưa đăng nhập hoặc là user ẩn danh thì trả về rỗng
            if (authentication == null || !authentication.isAuthenticated() ||
                    authentication instanceof AnonymousAuthenticationToken) {
                return Optional.empty();
            }

            // Lấy ID từ đối tượng User hiện tại (CustomeUserDetail)
            CustomeUserDetail userPrincipal = (CustomeUserDetail) authentication.getPrincipal();
            return Optional.ofNullable(userPrincipal.getId());
        };
    }
}
