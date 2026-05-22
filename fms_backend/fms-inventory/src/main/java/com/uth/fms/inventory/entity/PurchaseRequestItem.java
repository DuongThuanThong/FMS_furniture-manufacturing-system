package com.uth.fms.inventory.entity;

import jakarta.persistence.*;
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
@Table(name = "purchase_request_items")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PurchaseRequestItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_request_id", nullable = false)
    PurchaseRequest purchaseRequest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id", nullable = false)
    Material material;

    @Column(name = "qty_requested", nullable = false,precision = 10, scale = 3 )
    BigDecimal qtyRequested;

    @Builder.Default
    @Column(name = "qty_received", nullable = false)
    BigDecimal qtyReceived = BigDecimal.ZERO;

    @Column(name = "estimated_price", precision = 15, scale = 2)
    BigDecimal estimatedPrice;

    @Column(name = "actual_price", precision = 15, scale = 2)
    BigDecimal actualPrice;
}
