package com.uth.fms.order.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.OrderStatus;
import com.uth.fms.common.enums.CancelType;
import com.uth.fms.common.enums.PriceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.uth.fms.common.enums.OrderStatus;
import com.uth.fms.common.enums.CancelType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true) 
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order extends BaseEntity {

    @Column(name = "code", unique = true, nullable = false, length = 30)
    String code; 

    @Column(name = "quotation_id")
    Long quotationId; 

    @Column(name = "customer_id", nullable = false)
    Long customerId; 

    @Column(name = "sale_id", nullable = false)
    Long saleId; 

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    OrderStatus status; 

    @Column(name = "price_type", nullable = false, length = 10)
    PriceStatus priceType;

    @Column(name = "total_amount", precision = 15, scale = 2)
    BigDecimal totalAmount; 

    @Column(name = "final_amount", precision = 15, scale = 2)
    BigDecimal finalAmount; 

    @Column(name = "deposit_amount", precision = 15, scale = 2)
    @Builder.Default
    BigDecimal depositAmount = BigDecimal.ZERO; 

    @Column(name = "current_production_id")
    Long currentProductionId; 

    @Column(name = "expected_delivery_date")
    LocalDate expectedDeliveryDate; 

    @Column(name = "actual_delivery_date")
    LocalDate actualDeliveryDate; 

    @Column(name = "cancel_reason", columnDefinition = "TEXT")
    String cancelReason; 

    @Enumerated(EnumType.STRING)
    @Column(name = "cancel_type", length = 20)
    CancelType cancelType; 

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    List<OrderItem> items = new ArrayList<>();
}