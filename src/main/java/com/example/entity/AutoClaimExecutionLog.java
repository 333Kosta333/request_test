package com.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "auto_claim_execution_logs", indexes = {
        @Index(name = "idx_auto_claim_execution_logs_template_id", columnList = "template_id"),
        @Index(name = "idx_auto_claim_execution_logs_status", columnList = "status"),
        @Index(name = "idx_auto_claim_execution_logs_created_at", columnList = "created_at")
})
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoClaimExecutionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "template_id", nullable = false)
    private Long templateId;

    @Column(name = "execution_time", nullable = false)
    private Instant executionTime;

    @Column(nullable = false, length = 20)
    private String status;

    @Column(name = "response_payload", columnDefinition = "text")
    private String responsePayload;

    @Column(name = "error_message", columnDefinition = "text")
    private String errorMessage;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = Instant.now();
        }
        if (executionTime == null) {
            executionTime = Instant.now();
        }
    }
}
