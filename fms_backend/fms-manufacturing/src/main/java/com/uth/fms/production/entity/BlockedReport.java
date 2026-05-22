package com.uth.fms.production.entity;

import com.uth.fms.common.enums.ResolveType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "blocked_reports")
@EntityListeners(AuditingEntityListener.class)
public class BlockedReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "task_id", nullable = false)
    Long taskId;

    @Column(name = "reason_note", nullable = false, columnDefinition = "TEXT")
    String reasonNote;

    @Enumerated(EnumType.STRING)
    @Column(name = "resolve_type", length = 20)
    ResolveType resolveType;

    @Column(name = "reported_by", nullable = false)
    Long reportedBy;

    @Column(name = "resolved_by")
    Long resolvedBy;

    @Column(name = "resolve_note", columnDefinition = "TEXT")
    String resolveNote;

    @CreatedDate
    @Column(name = "create_at", updatable = false, nullable = false)
    LocalDateTime createTime;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    String createdBy;
}
