package com.uth.fms.inventory.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "material_categories")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MaterialCategory extends BaseEntity {

    @Column(name = "category_code", nullable = false, unique = true, length = 20)
    String categoryCode;

    @Column(nullable = false, length = 100)
    String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    MaterialCategory parent;
}
