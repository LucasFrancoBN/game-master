package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

public class Image {
    private String name;
    private String path;
    private String url;
    private ImageType type;
    private Long size;

    public Image() {
    }

    public Image(String name, String path, ImageType type, Long size, String url) {
        if(name.isBlank() || name.length() > 255) {
            throw new DomainValidationException("Nome não pode estar vazio ou maior que 255 caracteres");
        }

        if(path.isBlank() || path.length() > 500) {
            throw new DomainValidationException("Path não pode estar vazio ou maior que 500 caracteres");
        }

        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public ImageType getType() {
        return type;
    }

    public Long getSize() {
        return size;
    }

    public String getUrl() {
        return url;
    }
}
