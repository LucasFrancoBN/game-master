
package io.github.lucasfrancobn.gamemaster.infra.config.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import io.github.lucasfrancobn.gamemaster.application.usecase.image.CleanOrphanFilesUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
@Slf4j
public class FileCleanupScheduler {
    private final CleanOrphanFilesUsecase cleanOrphanFilesUsecase;


    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional
    public void scheduleFileCleanup() {
        try {
            cleanOrphanFilesUsecase.clean();
        } catch (Exception e) {
            log.error("An error occurred while cleaning orphaned files: {}", e.getMessage());
        }
    }
}
