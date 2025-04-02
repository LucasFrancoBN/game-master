package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.domain.entities.validation.product.ListImagesValidator;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private Integer amount;
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
            ProductStatus status,
            Integer amount
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.weight = weight;
        this.status = status;
        this.amount = amount;
    }

    public Product(String name, String description, BigDecimal price, Long weight, ProductStatus status, Integer amount) {
        setName(name);
        setDescription(description);
        setPrice(price);
        setWeight(weight);
        setStatus(status);
        setAmount(amount);
    }

    public void addImage(Image image) {
        if (!ListImagesValidator.isValid(images))
            throw new DomainValidationException("Muitas imagens.");
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

    public Integer getAmount() {
        return amount;
    }

    public void setName(String name) {
        if(name.isBlank() || name.length() < 3 || name.length() > 100) {
            throw new DomainValidationException("Nome precisa estar entre 3 e 100 caracteres");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if(description.isBlank() || description.length() < 30) {
            throw new DomainValidationException("Descrição precisa ter no mínimo 30 caracteres");
        }
        this.description = description;
    }

    public void setPrice(BigDecimal price) {
        if(price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new DomainValidationException("Preço precisa ser maior que 0");
        }
        this.price = price;
    }

    public void setWeight(Long weight) {
        if(weight.compareTo(0L) <= 0) {
            throw new DomainValidationException("Peso precisa ser maior que 0");
        }
        this.weight = weight;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public void setAmount(Integer amount) {
        if(amount.compareTo(0) < 0)
            throw new DomainValidationException("Quantidade precisa ser maior que 0");
        this.amount = amount;
    }
}
