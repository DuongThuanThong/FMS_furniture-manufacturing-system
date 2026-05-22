package com.uth.fms.inventory.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.PurchaseRequestStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "purchase_requests")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequest extends BaseEntity {

    @Column(nullable = false, unique = true)
    String code;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    PurchaseRequestStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    Supplier supplier;

    @Column(name = "approved_by")
    Long approvedBy;

    @Column(name = "requested_by", nullable = false)
    Long requestedBy;

    String note;
}
