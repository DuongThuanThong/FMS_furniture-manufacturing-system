package com.uth.fms.inventory.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StockSnapshotId implements Serializable {

    @Column(name = "material_id")
    Long materialId;

    @Column(name = "warehouse_id")
    Long warehouseId;

    @Column(name = "snapshot_date")
    LocalDate snapshotDate;
}
