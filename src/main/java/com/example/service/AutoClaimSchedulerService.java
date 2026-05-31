package com.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoClaimSchedulerService {

    private final AutoClaimProcessor autoClaimProcessor;
    private final AutoClaimLockManager lockManager;

    /**
     * Main scheduler that processes templates every 1 minute.
     */
    @Scheduled(fixedRate = 60000, initialDelay = 5000)
    public void scheduleProcessing() {
        log.debug("Scheduled processing started");
        autoClaimProcessor.processTemplates();
    }

    /**
     * Cleanup scheduler that runs every 5 minutes to remove expired locks.
     */
    @Scheduled(fixedRate = 300000, initialDelay = 10000)
    public void cleanupStaleLocks() {
        log.debug("Cleanup of stale locks started");
        lockManager.cleanupExpiredLocks(Duration.ofMinutes(20));
    }
}
