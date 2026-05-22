package com.uth.fms.order.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "deadline_change_logs")
@EntityListeners(AuditingEntityListener.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeadlineChangeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    Order order;

    @Column(name = "old_deadline", nullable = false)
    LocalDate oldDeadline;

    @Column(name = "new_deadline", nullable = false)
    LocalDate newDeadline;

    @Column(name = "reason_note", nullable = false, columnDefinition = "TEXT") 
    String reasonNote;

    @Column(name = "changed_by", nullable = false)
    Long changedBy;

    @CreatedDate
    @Column(name = "changed_at", updatable = false)
    LocalDateTime changedAt;
}
