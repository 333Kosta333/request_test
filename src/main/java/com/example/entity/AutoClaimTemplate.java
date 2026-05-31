package com.example.entity;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.Instant;
import java.util.Map;

@Entity
@Table(name = "auto_claim_templates")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AutoClaimTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(length = 255)
    private String serviceType;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private Map<String, Object> templateData;

    @Column(name = "interval_seconds")
    private Integer intervalSeconds = 3600;

    @Column(name = "last_execution_time")
    private Instant lastExecutionTime;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
