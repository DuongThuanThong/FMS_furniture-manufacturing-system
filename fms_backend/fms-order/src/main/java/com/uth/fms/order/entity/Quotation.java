package com.uth.fms.order.entity;

import com.uth.fms.common.entity.BaseEntity;
import com.uth.fms.common.enums.QuotationStatus;
import com.uth.fms.common.enums.PriceStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.uth.fms.common.enums.QuotationStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@Table(name = "quotations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true) 
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Quotation extends BaseEntity {

    @Column(name = "code", unique = true, nullable = false, length = 30)
    String code;

    @Column(name = "customer_id", nullable = false)
    Long customerId;

    @Column(name = "sale_id", nullable = false)
    Long saleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "price_type", nullable = false, length = 10)
    PriceStatus priceType;

    @Column(name = "subtotal", precision = 15, scale = 2)
    BigDecimal subtotal;

    @Column(name = "discount", precision = 15, scale = 2)
    @Builder.Default
    BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "total_amount", precision = 15, scale = 2)
    BigDecimal totalAmount;

    @Column(name = "final_quoted_amount", precision = 15, scale = 2)
    BigDecimal finalQuotedAmount;

    @Column(name = "requires_director_approval")
    @Builder.Default
    Boolean requiresDirectorApproval = false;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    QuotationStatus status;

    @Column(name = "valid_until")
    LocalDate validUntil;

    @Column(name = "approved_by")
    Long approvedBy;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    List<QuotationItem> items = new ArrayList<>();
}