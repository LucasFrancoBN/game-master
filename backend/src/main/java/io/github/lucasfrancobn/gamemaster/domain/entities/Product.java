package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long weight;
    private ProductStatus status;
    private final List<Image> images = new ArrayList<>();

    public Product() {
    }

    public Product(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            Long weight,
            ProductStatus status
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.status = status;
    }

    public Product(String name, String description, BigDecimal price, Long weight, ProductStatus status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.status = status;
    }

    public void addImage(Image image) {
//        if (!ListImagesValidator.isValid(images))
//            throw new RuntimeException("Too many images");

        this.images.add(image);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Long getWeight() {
        return weight;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setName(String name) {
        if(name.isBlank() || name.length() < 3 || name.length() > 100) {
            throw new IllegalArgumentException("Name must be between 3 and 100 characters");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if(description.isBlank() || description.length() < 3 || description.length() > 500) {
            throw new IllegalArgumentException("Description must be between 3 and 500 characters");
        }
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.price = price;
    }

    public void setWeight(Long weight) {
        if(weight.compareTo(0L) <= 0) {
            throw new IllegalArgumentException("Weight must be greater than zero");
        }
        this.weight = weight;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }
}
