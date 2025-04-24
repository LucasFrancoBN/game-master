package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.usecase.image.DeleteImage;
import io.github.lucasfrancobn.gamemaster.application.usecase.image.UpdateImageIndex;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.request.UpdateImageIndexRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gamemastermanager/api/v1/images")
@Slf4j
public class ImageController {
    private final UpdateImageIndex updateImageIndex;
    private final DeleteImage deleteImage;

    @PutMapping
    public ResponseEntity<Void> updateImageIndex(@RequestBody List<UpdateImageIndexRequest> updateImageIndices) {
        List<Image> images = updateImageIndices.stream().map(ImageMapper::toDomain).toList();
        log.info("Starting update image indices process");
        updateImageIndex.updateIndex(images);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<Void> deleteImage(@PathVariable String name) {
        this.deleteImage.delete(name);

        return ResponseEntity.noContent().build();
    }
}
