package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.usecase.product.RegisterProduct;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.exception.product.ReadImageException;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.RegisterProductRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ProductMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gamemastermanager/api/v1/products")
@Slf4j
public class ProductController {
    private final RegisterProduct registerProduct;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> registerProduct(
            @RequestPart("product_data") @Valid RegisterProductRequest product,
            @RequestPart("images")List<MultipartFile> images
    ) {
        log.info("Starting register product process. Product data: {} | Product images: {}", product, images);
        List<byte[]> content = images.stream().map(i -> {
            try {
                return i.getBytes();
            } catch (IOException e) {
                throw new ReadImageException("Falha ao ler as imagens: " + e.getMessage());
            }
        }).toList();
        List<String> filenames = images.stream().map(MultipartFile::getOriginalFilename).toList();

        Product domainProduct = ProductMapper.toDomain(product);

        registerProduct.registerProduct(domainProduct, content, filenames);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
