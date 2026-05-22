package com.uth.fms.product.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductBomResponse {
    Long id;
    Long materialId;
    String materialName; // Sẽ được map sau nếu cần
    BigDecimal quantity;
    BigDecimal unitPrice;
    String unit;
    String description;
}