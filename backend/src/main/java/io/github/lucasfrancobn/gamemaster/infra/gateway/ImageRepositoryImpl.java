package io.github.lucasfrancobn.gamemaster.infra.gateway;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ImageRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepository {
    private final ImageRepositoryJpa repositoryJpa;

    @Override
    @Transactional(readOnly = true)
    public List<Image> listAllImages() {
        return repositoryJpa.findAll().stream().map(ImageMapper::toDomain).toList();
    }
}
