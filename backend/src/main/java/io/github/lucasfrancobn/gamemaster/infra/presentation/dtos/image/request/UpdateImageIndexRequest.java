package io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.request;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;

public record UpdateImageIndexRequest(
        String name,
        String path,
        ImageType type,
        Long size,
        String url,
        Integer index
) {
}
