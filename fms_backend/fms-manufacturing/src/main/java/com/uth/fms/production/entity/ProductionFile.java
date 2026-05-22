package com.uth.fms.production.entity;

import com.uth.fms.common.enums.ApprovalStatus;
import com.uth.fms.common.enums.FileType;
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
@Table(name = "production_files")
@EntityListeners(AuditingEntityListener.class)
public class ProductionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "production_order_id", nullable = false)
    Long productionOrderId;

    @Column(name = "task_id")
    Long taskId;

    @Enumerated(EnumType.STRING)
    @Column(name = "file_type", nullable = false, length = 30)
    FileType fileType;

    @Column(name = "file_url", nullable = false, length = 500)
    String fileUrl;

    @Builder.Default
    @Column(nullable = false)
    Integer version = 1;

    @Builder.Default
    @Column(name = "is_latest", nullable = false)
    Boolean isLatest = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status", length = 20)
    ApprovalStatus approvalStatus;

    @CreatedBy
    @Column(name = "uploaded_by", nullable = false)
    Long uploadedBy;

    @CreatedDate
    @Column(name = "create_at", updatable = false, nullable = false)
    LocalDateTime createTime;
}
