package io.github.lucasfrancobn.gamemaster.infra.persistence.model;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    private String name;
    @Column(nullable = false, unique = true, length = 500)
    private String path;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ImageType type;
    @Column(nullable = false)
    private Long size;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;


}
