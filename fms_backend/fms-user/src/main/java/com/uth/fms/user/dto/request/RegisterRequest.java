package com.uth.fms.user.dto.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class RegisterRequest {
    String username;
    String password;
    String fullName;
    String phoneNumber;
    String email;
    String role;
}
