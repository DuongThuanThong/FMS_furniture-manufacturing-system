package com.uth.fms.production.entity;

import com.uth.fms.common.enums.QcFailType;
import com.uth.fms.common.enums.QcResult;
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
@Table(name = "qc_logs")
@EntityListeners(AuditingEntityListener.class)
public class QcLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "task_id", nullable = false)
    Long taskId; //productionTaskId

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    QcResult result;

    @Enumerated(EnumType.STRING)
    @Column(name = "fail_type", length = 30)
    QcFailType failType;

    @Column(nullable = false, columnDefinition = "TEXT")
    String note;

    @CreatedDate
    @Column(name = "create_at", updatable = false, nullable = false)
    LocalDateTime createTime;

    @LastModifiedDate
    @Column(name = "update_at", nullable = false)
    LocalDateTime updateTime;

    @CreatedBy
    @Column(name = "created_by", updatable = false)
    String createdBy;

    @LastModifiedBy
    @Column(name = "updated_by")
    String updatedBy;

}
