package com.uth.fms.order.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemResponse {
    Long id;
    Long productTemplateId;
    String productName;
    Integer quantity;
    BigDecimal unitPrice;
    BigDecimal amount;
    String note;
}