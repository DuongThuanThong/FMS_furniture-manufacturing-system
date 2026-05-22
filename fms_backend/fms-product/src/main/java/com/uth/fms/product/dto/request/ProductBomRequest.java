package com.uth.fms.product.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBomRequest {
    @NotNull(message = "Material ID không được để trống")
    Long materialId;

    @NotNull(message = "Số lượng không được để trống")
    @DecimalMin(value = "0.0", inclusive = false, message = "Số lượng phải lớn hơn 0")
    BigDecimal quantity;

    @DecimalMin(value = "0.0", message = "Đơn giá không được nhỏ hơn 0")
    BigDecimal unitPrice;

    @Size(max = 20, message = "Đơn vị tối đa 20 ký tự")
    String unit;

    @Size(max = 255, message = "Mô tả tối đa 255 ký tự")
    String description;
}