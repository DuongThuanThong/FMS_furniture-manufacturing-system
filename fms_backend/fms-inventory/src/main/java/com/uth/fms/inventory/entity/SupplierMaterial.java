package com.uth.fms.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "supplier_materials")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierMaterial {

    @EmbeddedId
    SupplierMaterialId id;

    @Column(name = "reference_price")
    BigDecimal referencePrice;

    @Column(name = "lead_time_days")
    Integer leadTimeDays;

    @Column(name = "is_preferred")
    Boolean preferred;
}
