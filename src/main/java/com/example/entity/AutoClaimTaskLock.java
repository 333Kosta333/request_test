package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "auto_claim_task_locks", indexes = {
        @Index(name = "idx_auto_claim_task_locks_status", columnList = "status"),
        @Index(name = "idx_auto_claim_task_locks_expires", columnList = "expires_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoClaimTaskLock {

    @Id
    private Long templateId;

    @Column(nullable = false, length = 50)
    private String status = "PENDING";

    @Column(nullable = false, length = 255, unique = true)
    private String lockToken;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @Column(name = "expires_at", nullable = false)
    private Instant expiresAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (updatedAt == null) {
            updatedAt = Instant.now();
        }
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = Instant.now();
    }
}
