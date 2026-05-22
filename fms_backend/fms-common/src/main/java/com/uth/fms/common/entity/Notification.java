package com.uth.fms.common.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "recipient_id", nullable = false)
    Long recipientId; 

    @Column(name = "type", nullable = false, length = 50)
    String type;

    @Column(name = "title", nullable = false, length = 200)
    String title;

    @Column(name = "message", columnDefinition = "TEXT")
    String message;

    @Column(name = "ref_type", length = 30)
    String refType;

    @Column(name = "ref_id")
    Long refId;

    @Column(name = "is_read")
    @Builder.Default
    Boolean isRead = false; 

    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        if (this.createdAt == null) {
            this.createdAt = LocalDateTime.now();
        }
    }
}