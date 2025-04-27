package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.application.maintenance.CleanOrphanFiles;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MaintenanceConfig {
    @Bean
    public CleanOrphanFiles cleanOrphanFilesUsecase(ProductRepository productRepository, FileCleanupService fileCleanupService) {
        return new CleanOrphanFiles(productRepository, fileCleanupService);
    }
}
