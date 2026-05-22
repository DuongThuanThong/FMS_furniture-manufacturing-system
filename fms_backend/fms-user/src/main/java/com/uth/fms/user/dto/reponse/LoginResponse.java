package com.uth.fms.user.dto.reponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder(toBuilder = true)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    String accessToken;
    String refreshToken;
    @Builder.Default
    String tokenType = "Bearer";
    String username;
    String fullName;
    String roles;
}