
package io.github.lucasfrancobn.gamemaster.infra.config.scheduler;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import io.github.lucasfrancobn.gamemaster.application.usecase.image.CleanOrphanFilesUsecase;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class FileCleanupScheduler {
    private final CleanOrphanFilesUsecase cleanOrphanFilesUsecase;


    @Scheduled(cron = "0 0 3 * * ?")
    @Transactional
    public void scheduleFileCleanup() {
        try {
            cleanOrphanFilesUsecase.clean();
        } catch (Exception e) {
            // TODO - adicionar log de erro
        }
    }
}
