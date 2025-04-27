package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

public class Image {
    private String name;
    private String path;
    private String url;
    private ImageType type;
    private Long size;
    private Integer index;
    private Product product;

    public Image() {
    }

    public Image(String name, String path, ImageType type, Long size, String url, Integer index) {
        setName(name);
        setPath(path);
        setType(type);
        setSize(size);
        setUrl(url);
        setIndex(index);
    }

    public Image(String name, String path, ImageType type, Long size, String url, Integer index, Product product) {
        setName(name);
        setPath(path);
        setUrl(url);
        setType(type);
        setSize(size);
        setIndex(index);
        setProduct(product);
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

    public Integer getIndex() {
        return index;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        if(product == null) {
            throw new DomainValidationException("O produto associado a imagem não pode ser nulo");
        }

        this.product = product;
    }

    public void setName(String name) {
        if(name.isBlank() || name.length() > 255) {
            throw new DomainValidationException("Nome não pode estar vazio ou maior que 255 caracteres");
        }

        this.name = name;
    }

    public void setPath(String path) {
        if(path.isBlank() || path.length() > 500) {
            throw new DomainValidationException("Path não pode estar vazio ou maior que 500 caracteres");
        }

        this.path = path;
    }

    public void setUrl(String url) {
        if (url == null || url.isBlank() || url.length() > 500) {
            throw new DomainValidationException("URL não pode ser nula, vazia ou maior que 500 caracteres");
        }

        this.url = url;
    }

    public void setType(ImageType type) {
        if (type == null) {
            throw new DomainValidationException("Tipo da imagem não pode ser nulo");
        }

        this.type = type;
    }

    public void setSize(Long size) {
        if (size == null || size <= 0) {
            throw new DomainValidationException("Tamanho deve ser um número positivo");
        }

        this.size = size;
    }

    public void setIndex(Integer index) {
        if (index == null || index < 0 || index > 4) {
            throw new DomainValidationException("Índice inválido (deve estar entre 0 e 4)");
        }

        this.index = index;
    }

    public void updateIndex(Integer index) {
        if (index == null || index < 0 || index > 4) {
            throw new DomainValidationException("Índice inválido (deve estar entre 0 e 4)");
        }

        this.index = index;
    }

    @Override
    public String toString() {
        return "Image{" +
                "name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", type=" + type +
                ", size=" + size +
                ", index=" + index +
                ", product=" + product +
                '}';
    }
}
