package com.uth.fms.inventory.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.TransactionType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
@Table(name = "stock_transactions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockTransaction extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    Material material;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id", nullable = false)
    Warehouse warehouse;

    @Enumerated(EnumType.STRING)
    @Column(name = "transaction_type", nullable = false)
    TransactionType transactionType;

    @Column(nullable = false, precision = 12, scale = 3)
    BigDecimal quantity;

    @Column(name = "reference_type", length = 30)
    String referenceType;

    @Column(name = "reference_id")
    Long referenceId;

    @Column(name = "performed_by")
    Long performedById;

    @Column(name = "lot_number", length= 50)
    String lotNumber;

    @Column(name = "unit_cost")
    BigDecimal unitCost;
}
