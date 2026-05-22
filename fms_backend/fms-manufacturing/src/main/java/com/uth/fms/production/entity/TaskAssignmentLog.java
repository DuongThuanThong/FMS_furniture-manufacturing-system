package com.uth.fms.production.entity;

import com.uth.fms.common.entity.BaseEntity;
// import com.uth.fms.user.entity.User;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
@Table(name = "task_assignment_logs")
@EntityListeners(AuditingEntityListener.class)
public class TaskAssignmentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    private ProductionTask task;

     @Column(name = "assigned_to", nullable = false)
     Long assignedTo;

     @Column(name = "assigned_by", nullable = false)
     Long assignedBy;

    @Column(columnDefinition = "text")
    private String reason;

    @CreatedDate
    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;
}