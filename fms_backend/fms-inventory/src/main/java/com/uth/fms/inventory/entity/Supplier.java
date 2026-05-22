package com.uth.fms.inventory.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "suppliers")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier extends BaseEntity {

    @Column(nullable = false, length = 100)
    String name;

    @Column(nullable = true)
    String phone;

    @Column(nullable = true)
    String email;

    @Column(nullable = true)
    String address;

    @Column(name = "tax_code")
    String taxCode;
}
