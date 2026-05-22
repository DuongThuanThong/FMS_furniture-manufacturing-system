package com.uth.fms.common.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
public class AuditLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 100)
    String action;

    @Column(name = "entity_type", nullable = false, length = 50)
    String entityType;

    @Column(name = "entity_id", nullable = false)
    Long entityId;

    @Column(name = "old_value", columnDefinition = "jsonb")
    String oldValue;

    @Column(name = "new_value", columnDefinition = "jsonb")
    String newValue;

    @Column(name = "ip_address", length = 45)
    String ipAddress;

    @CreatedDate
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;


}
