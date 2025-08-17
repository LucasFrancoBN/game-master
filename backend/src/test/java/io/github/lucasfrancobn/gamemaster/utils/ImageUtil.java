package io.github.lucasfrancobn.gamemaster.utils;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.image.request.UpdateImageIndexRequest;

import java.util.List;
import java.util.UUID;

public class ImageUtil {

    public static Image generateImage() {
        return new Image(
                "banner",
                "/images/banners",
                ImageType.PNG,
                1024L,
                "https://example.com/banner.png",
                0
        );
    }

    public static Image generateImage(Integer index) {
        return new Image(
                UUID.randomUUID().toString(),
                "/images/banners",
                ImageType.PNG,
                1024L,
                "https://example.com/banner.png",
                index
        );
    }

    public static List<byte[]> generateImageBytes() {
        return List.of(
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5});

    }

    public static List<byte[]> generateSixImageBytes() {
        return List.of(
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5},
                new byte[] {1, 2, 3, 4, 5});

    }

    public static List<String> generateFilenames() {
        return List.of(
                "first_filename",
                "second_filename",
                "thirty_filename",
                "fourth_filename",
                "fifth_filename",
                "sixth_filename");
    }

    public static List<String> generateSixFilenames() {
        return List.of(
                "first_filename",
                "second_filename",
                "thirty_filename",
                "fourth_filename",
                "fifth_filename",
                "sixth_filename");
    }

    public static UpdateImageIndexRequest generateImageIndex() {
        return new UpdateImageIndexRequest(
                "image",
                "image/path",
                ImageType.PNG,
                8L,
                "http://localhost:8080/image",
                1
        );
    }

    public static Image generateImageWithEmptyName() {
        return new Image(
                "",
                "/images/banners",
                ImageType.PNG,
                1024L,
                "https://example.com/banner.png",
                0
        );
    }

    public static Image generateImageWithLongName() {
        String longName = "a".repeat(256);
        return new Image(
                longName,
                "/images/banners",
                ImageType.PNG,
                1024L,
                "https://example.com/banner.png",
                0
        );
    }

    public static Image generateImageWithEmptyPath() {
        return new Image(
                "banner",
                "",
                ImageType.PNG,
                1024L,
                "https://example.com/banner.png",
                0
        );
    }

    public static Image generateImageWithInvalidUrl() {
        return new Image(
                "banner",
                "/images/banners",
                ImageType.PNG,
                1024L,
                "invalid-url",
                0
        );
    }

    public static Image generateImageWithNullType() {
        return new Image(
                "banner",
                "/images/banners",
                null,
                1024L,
                "https://example.com/banner.png",
                0
        );
    }

    public static Image generateImageWithNegativeSize() {
        return new Image(
                "banner",
                "/images/banners",
                ImageType.PNG,
                -1L,
                "https://example.com/banner.png",
                0
        );
    }

    public static Image generateImageWithInvalidIndex() {
        return new Image(
                "banner",
                "/images/banners",
                ImageType.PNG,
                1024L,
                "https://example.com/banner.png",
                5
        );
    }

    public static Image generateImageWithNullProduct() {
        Image image = generateImage();
        image.setProduct(null);
        return image;
    }

}
