package com.uth.fms.production.entity;

import com.uth.fms.common.enums.BomType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "material_requirements")
@EntityListeners(AuditingEntityListener.class)
public class MaterialRequirement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "production_order_id", nullable = false)
    Long productionOrderId;

    @Column(name = "material_id", nullable = false)
    Long materialId;

    @Column(name = "qty_required", nullable = false)
    BigDecimal qtyRequired;

    @Builder.Default
    @Column(name = "qty_issued")
    BigDecimal qtyIssued = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    BomType source;

    @Builder.Default
    @Column(name = "revision_number")
    Integer revisionNumber = 1;
}
