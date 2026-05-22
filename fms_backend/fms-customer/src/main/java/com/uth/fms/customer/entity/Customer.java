package com.uth.fms.customer.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.user.entity.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "customers")
@EntityListeners(AuditingEntityListener.class)
public class Customer extends BaseEntity {

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "text")
    private String address;

    @Column(length = 20, name = "tax_code")
    private String taxCode;

    @Column(columnDefinition = "text")
    private String note;

     @Column(name = "assigned_sale")
     Long assignedSale;
}