package com.uth.fms.common.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    int status;
    String message; //Thông báo lỗi
    Object details; //Chi tiết lỗi

    @Builder.Default
    LocalDateTime timestamp =  LocalDateTime.now();
}
