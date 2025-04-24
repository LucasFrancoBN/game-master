package io.github.lucasfrancobn.gamemaster.infra.gateway;

import java.util.List;

import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ImageEntity;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import io.github.lucasfrancobn.gamemaster.application.gateway.ImageRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ImageRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ImageRepositoryImpl implements ImageRepository {
    private final ImageRepositoryJpa repositoryJpa;

    @Override
    @Transactional(readOnly = true)
    public List<Image> listAllImages() {
        log.debug("Getting all images");
        return repositoryJpa.findAll().stream().map(ImageMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Image getImageByName(String name) {
        log.debug("Getting image by name: {}", name);
        ImageEntity image = repositoryJpa.findByName(name);
        return ImageMapper.toDomainWithProduct(image);
    }

    @Override
    @Transactional
    public void saveAll(List<Image> images) {
        log.debug("Saving all images");
        List<ImageEntity> imageEntities = images.stream().map(ImageMapper::toEntityWithProduct).toList();
        repositoryJpa.saveAll(imageEntities);
    }

    @Override
    @Transactional
    public void delete(Image image) {
        log.debug("Deleting image: {}", image.getName());
        ImageEntity entity = ImageMapper.toEntity(image);
        repositoryJpa.delete(entity);
    }
}
