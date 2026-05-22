package com.uth.fms.user.entity;

import com.uth.fms.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "workers")
public class Worker extends BaseEntity {

    @Column(nullable = false, length = 100)
    String fullName;

    @Column(length = 20)
    String phone;

    @Column(nullable = false, length = 30)
    String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_leader_id")
    User teamLeader;

    @Column(nullable = false, precision = 10, scale = 2)
    BigDecimal dailyRate;

    @Builder.Default
    @Column(name = "is_active", nullable = false)
    Boolean isActive = true;

    @Column(name = "joined_date")
    LocalDate joinedDate;

    @Column(columnDefinition = "TEXT")
    String note;
}
