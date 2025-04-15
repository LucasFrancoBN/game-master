package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.response;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;

public record FullImage(
        String name,
        String path,
        ImageType type,
        Long size,
        String url
) {
}
