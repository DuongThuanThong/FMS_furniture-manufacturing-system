package com.uth.fms.inventory.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.MaterialStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "materials")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Material extends BaseEntity {

    @Column(name = "material_code", nullable = false, unique = true)
    String materialCode;

    @Column(nullable = false, length = 255)
    String name;

    @Column(nullable = false, length =20)
    String unit;

    @Builder.Default
    @Column(name = "current_stock", nullable = false, precision = 12, scale = 3)
    BigDecimal currentStock =  BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "reserved_stock", nullable = false, precision = 12, scale = 3)
    BigDecimal reservedStock = BigDecimal.ZERO;

    @Builder.Default
    @Column(name = "min_stock", nullable = false, precision = 12, scale = 3)
    BigDecimal minStock= BigDecimal.ZERO;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 3)
    BigDecimal unitPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    MaterialStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    MaterialCategory category;
}
