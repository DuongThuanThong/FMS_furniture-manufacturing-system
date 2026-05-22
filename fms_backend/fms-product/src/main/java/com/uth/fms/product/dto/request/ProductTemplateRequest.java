package com.uth.fms.product.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductTemplateRequest {
    @NotBlank(message = "Mã mẫu sản phẩm không được để trống")
    @Size(max = 50, message = "Mã mẫu sản phẩm tối đa 50 ký tự")
    @Pattern(regexp = "^[A-Z0-9-]+$", message = "Mã mẫu sản phẩm chỉ bao gồm chữ hoa, số và dấu gạch ngang")
    String code;

    @NotBlank(message = "Tên mẫu sản phẩm không được để trống")
    @Size(max = 255, message = "Tên mẫu sản phẩm tối đa 255 ký tự")
    String name;

    @Size(max = 500, message = "Mô tả tối đa 500 ký tự")
    String description;

    Long categoryId;

    @Size(max = 100, message = "Đơn vị tối đa 100 ký tự")
    String unit;

    @DecimalMin(value = "0.0", message = "Giá mặc định không được nhỏ hơn 0")
    BigDecimal defaultPrice;

    @Size(max = 100, message = "Kích thước tối đa 100 ký tự")
    String dimensions;

    Boolean isActive;

    @Valid
    List<ProductBomRequest> boms;
}