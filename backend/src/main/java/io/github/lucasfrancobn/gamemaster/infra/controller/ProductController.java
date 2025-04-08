package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.application.usecase.product.PaginatedProducts;
import io.github.lucasfrancobn.gamemaster.application.usecase.product.RegisterProduct;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.infra.exception.product.ReadImageException;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.request.RegisterProductRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.product.response.ListProduct;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ProductMapper;
import jakarta.annotation.security.PermitAll;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gamemastermanager/api/v1/products")
@Slf4j
public class ProductController {
    private final RegisterProduct registerProduct;
    private final PaginatedProducts paginatedProducts;

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

    @GetMapping
    public ResponseEntity<PaginatedResult<?>> products(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "10",required = false) Integer size,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String status
    ) {
        List<ProductStatus> statusList = null;
        if(status != null) {
            String[] statusArray = status.split(";");
            statusList = Arrays.stream(statusArray).map(ProductStatus::fromString).toList();
        }
        int currentPage = page > 0 ? page - 1 : 0;
        Pagination pagination = new Pagination(currentPage, size);

        PaginatedResult<Product> productPage = paginatedProducts.getProducts(pagination, name, statusList);
        List<ListProduct> listProduct = productPage.getContent().stream().map(ProductMapper::toListProduct).toList();
        PaginatedResult<ListProduct> listProductpage = new PaginatedResult<>(
                listProduct,
                productPage.getCurrentPage() + 1,
                productPage.getPageSize(),
                productPage.getTotalElements()
        );

        return ResponseEntity.ok(listProductpage);
    }
}
