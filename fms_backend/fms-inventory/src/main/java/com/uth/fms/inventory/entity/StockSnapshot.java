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
@Table(name = "stock_snapshots")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StockSnapshot {

    @EmbeddedId
    StockSnapshotId id;

    @Column(name = "qty_balance", nullable = false)
    BigDecimal qtyBalance;
}
