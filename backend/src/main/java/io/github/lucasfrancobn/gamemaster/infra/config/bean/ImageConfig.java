package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import io.github.lucasfrancobn.gamemaster.application.usecase.image.DeleteImage;
import io.github.lucasfrancobn.gamemaster.application.usecase.image.UpdateImageIndex;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.application.usecase.image.CleanOrphanFiles;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;

@Configuration
public class ImageConfig {
    @Bean
    public CleanOrphanFiles cleanOrphanFilesUsecase(ImageRepository imageRepository, FileCleanupService fileCleanupService) {
        return new CleanOrphanFiles(imageRepository, fileCleanupService);
    }

    @Bean
    public UpdateImageIndex updateImageIndex(ImageRepository imageRepository) {
        return new UpdateImageIndex(imageRepository);
    }

    @Bean
    public DeleteImage deleteImage(ImageRepository imageRepository, FileCleanupService fileCleanupService) {
        return new DeleteImage(imageRepository, fileCleanupService);
    }
}
