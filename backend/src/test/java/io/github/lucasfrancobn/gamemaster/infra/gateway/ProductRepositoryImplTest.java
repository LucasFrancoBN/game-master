package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.shared.filter.product.ProductFilter;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ProductEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ProductRepositoryJpa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.*;
import static org.mockito.Mockito.*;

public class ProductRepositoryImplTest {

    @Mock
    private ProductRepositoryJpa repositoryJpa;

    @InjectMocks
    private ProductRepositoryImpl productRepository;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldSaveProduct() {
        Product product = generateProduct();
        ProductEntity entity = new ProductEntity();
        entity.setId(product.getId());

        when(repositoryJpa.save(any(ProductEntity.class))).thenReturn(entity);

        productRepository.save(product);

        verify(repositoryJpa, times(1)).save(any(ProductEntity.class));
    }

    @Test
    void shouldFindAllProducts() {
        ProductEntity product = generateProductEntity();
        Pagination pagination = new Pagination(0, 10);

        Page<ProductEntity> page = new PageImpl<>(
                List.of(product), PageRequest.of(0, 10), 1
        );

        when(repositoryJpa.findAll(any(Specification.class), any(PageRequest.class))).thenReturn(page);

        PaginatedResult<Product> result = productRepository.findAll(pagination,
                new ProductFilter("Console", List.of(ProductStatus.AVAILABLE)));

        assertThat(result).isNotNull();
        assertThat(result.getPageSize()).isEqualTo(10);
        assertThat(result.getCurrentPage()).isEqualTo(0);
        assertThat(result.getTotalElements()).isEqualTo(1);

        verify(repositoryJpa, times(1)).findAll(any(Specification.class), any(PageRequest.class));
    }

    @Test
    void shouldFindProductById() {
        UUID id = UUID.randomUUID();
        ProductEntity entity = generateProductEntity(id);

        when(repositoryJpa.findById(any(UUID.class))).thenReturn(Optional.of(entity));

        Optional<Product> result = productRepository.findById(id);

        assertThat(result).isPresent();
        assertThat(result.get().getId()).isEqualTo(id);

        verify(repositoryJpa, times(1)).findById(id);
    }

    @Test
    void shouldReturnEmpty_WhenProductNotFoundById() {
        UUID id = UUID.randomUUID();

        when(repositoryJpa.findById(any(UUID.class))).thenReturn(Optional.empty());

        Optional<Product> result = productRepository.findById(id);

        assertThat(result).isEmpty();

        verify(repositoryJpa, times(1)).findById(id);
    }

    @Test
    void shouldFindAllImages() {
        when(repositoryJpa.findAllImages()).thenReturn(List.of());

        List<Image> result = productRepository.findAllImages();

        assertThat(result).isNotNull();
        verify(repositoryJpa, times(1)).findAllImages();
    }

    @Test
    void shouldDeleteProduct() {
        Product product = generateProduct();

        doNothing().when(repositoryJpa).delete(any(ProductEntity.class));

        productRepository.delete(product);

        verify(repositoryJpa, times(1)).delete(any(ProductEntity.class));
    }
}
