package com.uth.fms.product.dto.response;

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
public class ProductTemplateResponse {
    Long id;
    String code;
    String name;
    String description;
    Long categoryId;
    String categoryName; // Sẽ được map sau
    String unit;
    BigDecimal defaultPrice;
    String dimensions;
    String status; // ACTIVE dựa trên isActive
    Boolean isActive;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    Long createdBy;
    Long updatedBy;
    List<ProductBomResponse> boms;
}