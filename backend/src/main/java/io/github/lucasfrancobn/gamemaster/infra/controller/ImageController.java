package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.usecase.image.UpdateImageIndex;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.request.UpdateImageIndexRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gamemastermanager/api/v1/images")
@Slf4j
public class ImageController {
    private final UpdateImageIndex updateImageIndex;

    @PutMapping
    public ResponseEntity<Void> updateImageIndex(@RequestBody List<UpdateImageIndexRequest> updateImageIndices) {
        List<Image> images = updateImageIndices.stream().map(ImageMapper::toDomain).toList();
        log.info("Starting update image indices process");
        updateImageIndex.updateIndex(images);

        return ResponseEntity.noContent().build();
    }
}
