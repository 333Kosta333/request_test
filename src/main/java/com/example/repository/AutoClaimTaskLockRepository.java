package com.example.repository;

import com.example.entity.AutoClaimTaskLock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutoClaimTaskLockRepository extends JpaRepository<AutoClaimTaskLock, Long> {

    Optional<AutoClaimTaskLock> findByTemplateId(Long templateId);

    List<AutoClaimTaskLock> findByStatus(String status);

    @Query("SELECT t FROM AutoClaimTaskLock t WHERE t.expiresAt < :now AND t.status = 'IN_PROGRESS'")
    List<AutoClaimTaskLock> findExpiredLocks(Instant now);

    @Modifying
    @Transactional
    @Query("UPDATE AutoClaimTaskLock t SET t.status = :newStatus, t.updatedAt = :now WHERE t.templateId = :templateId")
    void updateStatus(@Param("templateId") Long templateId, @Param("newStatus") String newStatus, @Param("now") Instant now);

    @Modifying
    @Transactional
    @Query("DELETE FROM AutoClaimTaskLock t WHERE t.expiresAt < :now AND t.status = 'IN_PROGRESS'")
    void deleteExpiredLocks(Instant now);
}
