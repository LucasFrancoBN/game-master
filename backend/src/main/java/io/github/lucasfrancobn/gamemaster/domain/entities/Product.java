package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.domain.entities.validation.product.ListImagesValidator;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

public class Product {
    private UUID id;
    private String name;
    private String description;
    private Integer amount;
    private BigDecimal price;
    private Long weight;
    private ProductStatus status;
    private final List<Image> images = new ArrayList<>();

    public Product(
            UUID id,
            String name,
            String description,
            BigDecimal price,
            Long weight,
            ProductStatus status,
            Integer amount
    ) {
        if(id == null)
            throw new DomainValidationException("Id não pode ser nulo");

        this.id = id;
        setName(name);
        setDescription(description);
        setPrice(price);
        setWeight(weight);
        setStatus(status);
        setAmount(amount);
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
        if(status == null)
            throw new DomainValidationException("Status não pode ser nulo");

        this.status = status;
    }

    public void setAmount(Integer amount) {
        if(amount.compareTo(0) < 0)
            throw new DomainValidationException("Quantidade precisa ser maior que 0");
        this.amount = amount;
    }

    public void updateProduct(Product product) {
        setPrice(product.getPrice());
        setAmount(product.getAmount());
        setDescription(product.getDescription());
        setName(product.getName());
        setStatus(product.getStatus());
        setWeight(product.getWeight());
    }

    public void updateImageIndex(List<Image> newImages) {
        if(newImages == null || newImages.isEmpty())
            throw new DomainValidationException("Lista de novas imagens está vazia");

        Map<String, Integer> newIndices = newImages.stream().collect(Collectors.toMap(Image::getName, Image::getIndex));

        for (Image image : images) {
            Integer newIndex = newIndices.get(image.getName());
            image.updateIndex(newIndex);
        }
    }

    public void removeImage(String name) {
        boolean removed = images.removeIf(image -> image.getName().equals(name));

        if(!removed)
            throw new DomainValidationException("Imagem não encontrada");

        reorderImages();
    }

    private void reorderImages() {
        for (int i = 0; i < images.size(); i++) {
            images.get(i).updateIndex(i);
        }
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", weight=" + weight +
                ", status=" + status +
                ", images=" + images +
                '}';
    }
}
