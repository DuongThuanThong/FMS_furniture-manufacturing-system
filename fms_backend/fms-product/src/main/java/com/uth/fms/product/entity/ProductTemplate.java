package com.uth.fms.product.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "product_templates")
@EntityListeners(AuditingEntityListener.class)
public class ProductTemplate extends BaseEntity {

    @Column(unique = true, nullable = false, length = 30)
    private String code;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(length = 50)
    private String category;

    @Column(precision = 15, scale = 2)
    private BigDecimal basePrice;

    @Builder.Default
    @Column(nullable = false)
    private Boolean isActive = true;
}