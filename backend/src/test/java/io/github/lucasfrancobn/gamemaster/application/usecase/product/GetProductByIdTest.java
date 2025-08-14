package io.github.lucasfrancobn.gamemaster.application.usecase.product;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class GetProductByIdTest {
    @Mock
    private ProductRepository repository;

    @InjectMocks
    private GetProductById useCase;

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
    void shouldGetProductById() {
        var productMock = generateProduct();
        var id = productMock.getId();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.of(productMock));

        var searchedProduct = useCase.getProductById(id);

        assertThat(searchedProduct)
                .extracting(
                        Product::getId,
                        Product::getName,
                        Product::getPrice,
                        Product::getAmount,
                        Product::getStatus,
                        Product::getWeight,
                        Product::getDescription
                )
                .containsExactly(
                        productMock.getId(),
                        productMock.getName(),
                        productMock.getPrice(),
                        productMock.getAmount(),
                        productMock.getStatus(),
                        productMock.getWeight(),
                        productMock.getDescription()
                );

        verify(repository, times(1)).findById(any(UUID.class));
    }

    @Test
    void shouldThrowException_WhenGetProductById_IdIsNull() {
        UUID id = null;

        assertThatThrownBy(() -> useCase.getProductById(id))
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Id do produto não pode ser nulo");

        verify(repository, never()).findById(any(UUID.class));

    }

    @Test
    void shouldThrowException_WhenGetProductById_ProductNotFound() {
        UUID id = UUID.randomUUID();

        when(repository.findById(any(UUID.class))).thenReturn(Optional.empty());

        assertThatThrownBy(() -> useCase.getProductById(id))
                .isNotNull()
                .isInstanceOf(ProductNotFoundException.class)
                .hasMessage("Produto não encontrado");

        verify(repository, times(1)).findById(any(UUID.class));

    }
}
