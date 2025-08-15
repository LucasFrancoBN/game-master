package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.application.shared.filter.product.ProductFilter;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.Pagination;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static io.github.lucasfrancobn.gamemaster.utils.PaginationUtil.generatePaginatedResult;
import static io.github.lucasfrancobn.gamemaster.utils.PaginationUtil.generatePagination;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class PaginatedProductsTest {
    @Mock
    private ProductRepository repository;
    
    @InjectMocks
    private PaginatedProducts useCase;
    
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception{
        openMocks.close();
    }

    @Test
    void shouldGetPaginatedProducts() {
        var pagination = generatePagination();
        var name = "Product name";
        var status = List.of(ProductStatus.AVAILABLE);
        var paginatedProduct = generatePaginatedResult(name, status.getFirst());

        when(repository.findAll(any(Pagination.class), any(ProductFilter.class))).thenReturn(paginatedProduct);

        var products = useCase.getProducts(pagination, name, status);

        assertThat(products)
                .extracting(
                        PaginatedResult::getCurrentPage,
                        PaginatedResult::getPageSize,
                        PaginatedResult::getTotalElements
                )
                .containsExactly(
                        paginatedProduct.getCurrentPage(),
                        paginatedProduct.getPageSize(),
                        paginatedProduct.getTotalElements()
                );

        assertThat(products.getContent())
                .hasSize(paginatedProduct.getContent().size());

        assertThat(products.getContent().getFirst())
                .extracting(
                        Product::getId,
                        Product::getName,
                        Product::getStatus
                )
                .containsExactly(
                        paginatedProduct.getContent().getFirst().getId(),
                        name,
                        status.getFirst()
                );

        verify(repository, times(1)).findAll(any(Pagination.class), any(ProductFilter.class));
    }
}
