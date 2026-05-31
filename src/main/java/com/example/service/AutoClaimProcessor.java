package com.example.service;

import com.example.entity.AutoClaimTemplate;
import com.example.entity.AutoClaimTaskLock;
import com.example.repository.AutoClaimTemplateRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoClaimProcessor {

    private final AutoClaimTemplateRepository templateRepository;
    private final AutoClaimLockManager lockManager;
    private final AutoClaimExecutionLogService logService;
    private final RequestCreationService requestCreationService;

    @Transactional
    public void processTemplates() {
        log.info("Starting template processing...");

        try {
            // Get templates ready for execution
            List<AutoClaimTemplate> templates = templateRepository.findTemplatesReadyForExecution(Instant.now());

            if (templates.isEmpty()) {
                log.debug("No templates ready for execution");
                return;
            }

            log.info("Found {} templates ready for execution", templates.size());

            for (AutoClaimTemplate template : templates) {
                processTemplate(template);
            }

            log.info("Template processing completed");

        } catch (Exception e) {
            log.error("Error during template processing", e);
        }
    }

    @Transactional
    private void processTemplate(AutoClaimTemplate template) {
        log.info("Processing template: {} (id: {})", template.getName(), template.getId());

        try {
            // Try to acquire lock
            Optional<AutoClaimTaskLock> lock = lockManager.acquireLock(template.getId());

            if (lock.isEmpty()) {
                log.warn("Could not acquire lock for template: {}", template.getId());
                return;
            }

            try {
                // Create request from template
                Object response = createRequestFromTemplate(template);

                // Update template's last execution time
                template.setLastExecutionTime(Instant.now());
                templateRepository.save(template);

                // Update lock status to COMPLETED
                lockManager.updateStatus(template.getId(), "COMPLETED");

                // Save success result
                logService.saveSuccessResult(template.getId(), response);

                log.info("Template processed successfully: {} (id: {})", template.getName(), template.getId());

            } catch (Exception e) {
                log.error("Error processing template: {} (id: {})", template.getName(), template.getId(), e);

                // Update lock status to FAILED
                lockManager.updateStatus(template.getId(), "FAILED");

                // Save error result
                logService.saveErrorResult(template.getId(), e);

                // Optionally deactivate template if it fails too many times
                handleTemplateFailure(template);
            }

        } catch (Exception e) {
            log.error("Unexpected error while processing template: {} (id: {})", template.getName(), template.getId(), e);
        }
    }

    private Object createRequestFromTemplate(AutoClaimTemplate template) throws Exception {
        log.debug("Creating request from template: {}", template.getName());
        return requestCreationService.createRequestFromTemplate(template);
    }

    @Transactional
    private void handleTemplateFailure(AutoClaimTemplate template) {
        try {
            // Get recent failures
            List<AutoClaimExecutionLog> recentFailures = logService.getRecentFailedExecutions(template.getId(), 5);

            // If more than 3 failures in recent executions, deactivate template
            if (recentFailures.size() >= 3) {
                template.setIsActive(false);
                templateRepository.save(template);
                log.warn("Template deactivated due to repeated failures: {} (id: {})", template.getName(), template.getId());
            }
        } catch (Exception e) {
            log.error("Error handling template failure for: {} (id: {})", template.getName(), template.getId(), e);
        }
    }
}
