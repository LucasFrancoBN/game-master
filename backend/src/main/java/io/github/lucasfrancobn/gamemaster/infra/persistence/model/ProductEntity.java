package io.github.lucasfrancobn.gamemaster.infra.persistence.model;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Lob
    private String description;
    private BigDecimal price;
    private Long weight;
    @Enumerated(EnumType.STRING)
    private ProductStatus status;
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ImageEntity> images = new ArrayList<>();

}
