package io.github.lucasfrancobn.gamemaster.domain.entities.enums;

import java.util.Arrays;

public enum ImageType {
    PNG("image/png", "png"),
    JPEG("image/jpeg", "jpg"),
    JPG("jpg", "image/jpeg"),
    WEBP("image/webp", "webp");

    private final String mimeType;
    private final String extension;

    ImageType(String mimeType, String extension) {
        this.mimeType = mimeType;
        this.extension = extension;
    }

    public static ImageType fromExtension(String extension) {
        return Arrays.stream(values())
                .filter(it -> it.extension.equals(extension))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid image type: " + extension));
    }

    public String getMimeType() {
        return mimeType;
    }

    public String getExtension() {
        return extension;
    }
}
