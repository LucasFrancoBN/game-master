package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;

import java.util.UUID;

public class Image {
    private String name;
    private String path;
    private ImageType type;
    private Long size;

    public Image() {
    }

    public Image(String name, String path, ImageType type, Long size) {
        if(name.isBlank() || name.length() > 255) {
            throw new IllegalArgumentException("Name cannot be blank or longer than 255 characters");
        }

        if(path.isBlank() || path.length() > 500) {
            throw new IllegalArgumentException("Path cannot be blank or longer than 500 characters");
        }

        this.name = name;
        this.path = path;
        this.type = type;
        this.size = size;
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
}
