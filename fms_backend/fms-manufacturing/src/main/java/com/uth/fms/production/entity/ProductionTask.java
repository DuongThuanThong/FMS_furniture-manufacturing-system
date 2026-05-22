package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.user.entity.User;
import com.uth.fms.common.enums.TaskStatus;
import com.uth.fms.common.enums.TaskType;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "production_tasks")
@EntityListeners(AuditingEntityListener.class)
public class ProductionTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "production_order_id", nullable = false)
    private ProductionOrder productionOrder;

    @Column(name = "task_type", nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @Column(name = "sort_order", nullable = false)
    private Integer sortOrder;

    @Column(nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

     @Column(name = "assigned_to")
     Long assignedTo; //id user

    @Column(precision = 6, scale = 2, name = "estimated_hours")
    private BigDecimal estimatedHours;

    @Builder.Default
    @Column(precision = 6, scale = 2, name = "actual_hours")
    private BigDecimal actualHours = BigDecimal.ZERO;

    @Builder.Default
    @Column(precision = 12, scale = 2, name = "labor_cost")
    private BigDecimal laborCost = BigDecimal.ZERO;

    private LocalDate deadline;

    @CreatedDate
    @Column(name = "started_at")
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}