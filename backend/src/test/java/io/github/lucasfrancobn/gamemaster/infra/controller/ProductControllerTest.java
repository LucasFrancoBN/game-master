package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.exception.product.ProductNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.shared.pagination.PaginatedResult;
import io.github.lucasfrancobn.gamemaster.application.usecase.product.*;
import io.github.lucasfrancobn.gamemaster.domain.entities.Product;
import io.github.lucasfrancobn.gamemaster.infra.handler.GlobalException;
import io.github.lucasfrancobn.gamemaster.utils.JsonHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.ImageUtil.generateImageIndex;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {
    @Mock
    private RegisterProduct registerProduct;

    @Mock
    private PaginatedProducts paginatedProducts;

    @Mock
    private GetProductById productById;

    @Mock
    private UpdateProduct updateProduct;

    @Mock
    private AddImageToProduct addImageToProduct;

    @Mock
    private UpdateImageIndex updateImageIndex;

    @Mock
    private DeleteImage deleteImage;

    @Mock
    private DeleteProduct deleteProduct;

    @InjectMocks
    private ProductController controller;

    private MockMvc mockMvc;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        PageableHandlerMethodArgumentResolver pageableResolver = new PageableHandlerMethodArgumentResolver();
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(pageableResolver)
                .setControllerAdvice(GlobalException.class)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                })
                .build();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldDeleteProduct() throws Exception {
        var id = UUID.randomUUID();

        doNothing().when(deleteProduct).delete(any(UUID.class));

        mockMvc.perform(delete("/gamemastermanager/api/v1/products/{id}", id))
                .andExpect(status().isNoContent());

        verify(deleteProduct, times(1)).delete(any(UUID.class));
    }

    @Test
    void shouldDeleteImage() throws Exception {
        var id = UUID.randomUUID();
        var name = "image-name.jpg";

        doNothing().when(deleteImage).delete(id, name);

        mockMvc.perform(delete("/gamemastermanager/api/v1/products/{id}/image/{name}", id, name))
                .andExpect(status().isNoContent());

        verify(deleteImage, times(1)).delete(any(UUID.class), anyString());
    }

    @Nested
    class GetProduct {
        @Test
        void shouldGetProductById() throws Exception {
            var id = UUID.randomUUID();
            var product = generateProduct(id);

            when(productById.getProductById(any(UUID.class))).thenReturn(product);

            mockMvc.perform(get("/gamemastermanager/api/v1/products/{id}", id))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id").value(id.toString()))
                    .andExpect(jsonPath("$.name").value(product.getName()))
                    .andExpect(jsonPath("$.price").value(product.getPrice()));

            verify(productById, times(1)).getProductById(any(UUID.class));
        }

        @Test
        void shouldThrowException_WhenGetProductById_ProductNotFound() throws Exception {
            var id = UUID.randomUUID();

            when(productById.getProductById(any(UUID.class))).thenThrow(new ProductNotFoundException("Produto não encontrado"));

            mockMvc.perform(get("/gamemastermanager/api/v1/products/{id}", id))
                    .andExpect(status().isNotFound())
                    .andExpect(jsonPath("$.timestamp").exists())
                    .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                    .andExpect(jsonPath("$.message").value("Produto não encontrado"))
                    .andExpect(jsonPath("$.path").value("/gamemastermanager/api/v1/products/".concat(id.toString())));

            verify(productById, times(1)).getProductById(any(UUID.class));
        }
    }

    @Test
    void shouldRegisterProduct() throws Exception {
        var request = generateRegisterProduct();
        MockMultipartFile productData = new MockMultipartFile(
                "product_data", "", "application/json", JsonHelper.asBytes(request)
        );
        MockMultipartFile image = new MockMultipartFile(
                "images", "img.png", "image/png", "fake-content".getBytes()
        );

        doNothing().when(registerProduct).registerProduct(any(Product.class), anyList(), anyList());

        mockMvc.perform(multipart("/gamemastermanager/api/v1/products")
                        .file(productData)
                        .file(image)
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(registerProduct, times(1)).registerProduct(any(Product.class), anyList(), anyList());
    }

    @Test
    void shouldListProducts() throws Exception {
        var product = generateProduct(UUID.randomUUID());
        var paginatedResult = new PaginatedResult<>(List.of(product), 0, 10, 1L);

        when(paginatedProducts.getProducts(any(), any(), any())).thenReturn(paginatedResult);

        mockMvc.perform(get("/gamemastermanager/api/v1/products")
                        .param("page", "1")
                        .param("size", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(product.getId().toString()))
                .andExpect(jsonPath("$.content[0].name").value(product.getName()));

        verify(paginatedProducts, times(1)).getProducts(any(), any(), any());
    }

    @Test
    void shouldUpdateProduct() throws Exception {
        var id = UUID.randomUUID();
        var request = generateUpdateProduct();

        doNothing().when(updateProduct).update(any(UUID.class), any(Product.class));

        mockMvc.perform(put("/gamemastermanager/api/v1/products/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(request)))
                .andExpect(status().isNoContent());

        verify(updateProduct, times(1)).update(eq(id), any(Product.class));
    }

    @Test
    void shouldAddImagesToProduct() throws Exception {
        var id = UUID.randomUUID();
        MockMultipartFile image = new MockMultipartFile(
                "images", "img.png", "image/png", "fake-content".getBytes()
        );

        doNothing().when(addImageToProduct).add(any(UUID.class), anyList(), anyList());

        mockMvc.perform(multipart("/gamemastermanager/api/v1/products/{id}", id)
                        .file(image)
                        .with(req -> { req.setMethod("PATCH"); return req; }))
                .andExpect(status().isNoContent());

        verify(addImageToProduct, times(1)).add(eq(id), anyList(), anyList());
    }

    @Test
    void shouldUpdateImageIndex() throws Exception {
        var id = UUID.randomUUID();
        var request = List.of(generateImageIndex());

        doNothing().when(updateImageIndex).updateIndex(any(UUID.class), anyList());

        mockMvc.perform(put("/gamemastermanager/api/v1/products/{id}/image/update-index", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(JsonHelper.asJsonString(request)))
                .andExpect(status().isNoContent());

        verify(updateImageIndex, times(1)).updateIndex(eq(id), anyList());
    }
}
