package com.uth.fms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

/**
 * Điểm khởi chạy duy nhất của toàn bộ hệ thống FMS (Modular Monolith).
 * scanBasePackages = "com.uth.fms" giúp Spring Boot tự động quét
 * và đăng ký tất cả Bean từ mọi module (common, user, order, ...).
 */
@SpringBootApplication(scanBasePackages = "com.uth.fms")
public class FmsApplication {

    public static void main(String[] args) {
        // PostgreSQL chỉ chấp nhận tên chuẩn IANA "Asia/Ho_Chi_Minh".
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));

        SpringApplication.run(FmsApplication.class, args);
    }
}
