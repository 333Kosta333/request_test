package com.example.service;

import com.example.entity.AutoClaimTaskLock;
import com.example.repository.AutoClaimTaskLockRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoClaimLockManager {

    private final AutoClaimTaskLockRepository lockRepository;

    @Transactional
    public Optional<AutoClaimTaskLock> acquireLock(Long templateId) {
        try {
            // Check if lock exists
            Optional<AutoClaimTaskLock> existingLock = lockRepository.findByTemplateId(templateId);

            if (existingLock.isPresent()) {
                AutoClaimTaskLock lock = existingLock.get();

                // If status is PENDING, try to acquire
                if ("PENDING".equals(lock.getStatus())) {
                    lock.setStatus("IN_PROGRESS");
                    lock.setLockToken(UUID.randomUUID().toString());
                    lock.setUpdatedAt(Instant.now());
                    lock.setExpiresAt(Instant.now().plus(Duration.ofMinutes(20)));
                    lockRepository.save(lock);
                    log.info("Lock acquired for template: {}", templateId);
                    return Optional.of(lock);
                } else if (lock.getExpiresAt().isBefore(Instant.now())) {
                    // If expired, reset to PENDING and try again
                    lock.setStatus("PENDING");
                    lock.setUpdatedAt(Instant.now());
                    lockRepository.save(lock);
                    return acquireLock(templateId);
                }

                log.debug("Lock not available for template: {} with status: {}", templateId, lock.getStatus());
                return Optional.empty();
            }

            // Create new lock
            AutoClaimTaskLock newLock = new AutoClaimTaskLock();
            newLock.setTemplateId(templateId);
            newLock.setStatus("IN_PROGRESS");
            newLock.setLockToken(UUID.randomUUID().toString());
            newLock.setCreatedAt(Instant.now());
            newLock.setUpdatedAt(Instant.now());
            newLock.setExpiresAt(Instant.now().plus(Duration.ofMinutes(20)));

            lockRepository.save(newLock);
            log.info("New lock created for template: {}", templateId);
            return Optional.of(newLock);

        } catch (Exception e) {
            log.error("Error acquiring lock for template: {}", templateId, e);
            return Optional.empty();
        }
    }

    @Transactional
    public void updateStatus(Long templateId, String status) {
        try {
            lockRepository.updateStatus(templateId, status, Instant.now());
            log.debug("Lock status updated for template: {} to: {}", templateId, status);
        } catch (Exception e) {
            log.error("Error updating lock status for template: {}", templateId, e);
        }
    }

    @Transactional
    public void releaseLock(Long templateId) {
        try {
            Optional<AutoClaimTaskLock> lock = lockRepository.findByTemplateId(templateId);
            if (lock.isPresent()) {
                lockRepository.delete(lock.get());
                log.info("Lock released for template: {}", templateId);
            }
        } catch (Exception e) {
            log.error("Error releasing lock for template: {}", templateId, e);
        }
    }

    @Transactional
    public void cleanupExpiredLocks(Duration timeout) {
        try {
            Instant expirationTime = Instant.now().minus(timeout);
            List<AutoClaimTaskLock> expiredLocks = lockRepository.findExpiredLocks(expirationTime);

            for (AutoClaimTaskLock lock : expiredLocks) {
                lock.setStatus("PENDING");
                lock.setUpdatedAt(Instant.now());
                lockRepository.save(lock);
                log.info("Expired lock cleaned up for template: {}", lock.getTemplateId());
            }

            log.info("Cleanup completed. Found and reset {} expired locks", expiredLocks.size());
        } catch (Exception e) {
            log.error("Error during cleanup of expired locks", e);
        }
    }
}
