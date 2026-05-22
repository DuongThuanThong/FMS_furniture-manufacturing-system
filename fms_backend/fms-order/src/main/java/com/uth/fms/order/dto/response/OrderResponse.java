package com.uth.fms.order.dto.response;

import com.uth.fms.common.enums.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    Long id;
    String orderCode;
    Long customerId;
    String customerName;
    java.time.LocalDate orderDate;
    java.time.LocalDate deliveryDate;
    OrderStatus status;
    BigDecimal totalAmount;
    String note;
    List<OrderItemResponse> items;
    LocalDateTime createTime;
    LocalDateTime updateTime;
}