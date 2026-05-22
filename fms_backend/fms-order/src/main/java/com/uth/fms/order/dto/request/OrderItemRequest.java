package com.uth.fms.order.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderItemRequest {
    @NotNull(message = "Mẫu sản phẩm không được để trống")
    Long productTemplateId;

    @NotNull(message = "Số lượng không được để trống")
    @DecimalMin(value = "1", message = "Số lượng phải ít nhất là 1")
    Integer quantity;

    @NotNull(message = "Đơn giá không được để trống")
    @DecimalMin(value = "0.0", message = "Đơn giá không được nhỏ hơn 0")
    BigDecimal unitPrice;

    String note;
}