package io.github.lucasfrancobn.gamemaster.domain.entities.validation.product;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;

import java.util.List;

public class ListImagesValidator {
    public static boolean isValid(List<Image> images) {
        return images != null && !images.isEmpty() && images.size() < 5;
    }
}
