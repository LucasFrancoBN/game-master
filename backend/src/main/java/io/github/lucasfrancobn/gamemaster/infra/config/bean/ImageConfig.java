package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.application.usecase.image.CleanOrphanFilesUsecase;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

@Configuration
public class ImageConfig {
    @Bean
    public CleanOrphanFilesUsecase cleanOrphanFilesUsecase(ImageRepository imageRepository, FileCleanupService fileCleanupService) {
        return new CleanOrphanFilesUsecase(imageRepository, fileCleanupService);
    }   
}
