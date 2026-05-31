package com.example.repository;

import com.example.entity.AutoClaimTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AutoClaimTemplateRepository extends JpaRepository<AutoClaimTemplate, Long> {

    List<AutoClaimTemplate> findByIsActiveTrue();

    @Query("SELECT t FROM AutoClaimTemplate t WHERE t.isActive = true " +
           "AND (t.lastExecutionTime IS NULL OR t.lastExecutionTime + CAST(t.intervalSeconds AS LONG) * 1000 < :now)")
    List<AutoClaimTemplate> findTemplatesReadyForExecution(Instant now);
}
