package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ProductStatus;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;
import io.github.lucasfrancobn.gamemaster.utils.ProductUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProduct;
import static io.github.lucasfrancobn.gamemaster.utils.ProductUtil.generateProductWithMaxImages;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ProductTest {
    @Test
    void shouldInstantiateProduct() {
        Product product = generateProductWithMaxImages();

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(product).as("product").isNotNull().isInstanceOf(Product.class);
        soft.assertThat(product.getId()).as("product.id").isNotNull().isInstanceOf(UUID.class);
        soft.assertThat(product.getAmount()).as("product.amount").isNotNull().isGreaterThanOrEqualTo(0);
        soft.assertThat(product.getPrice()).as("product.price").isNotNull().isPositive();
        soft.assertThat(product.getName()).as("product.name").isNotNull().isNotBlank();
        soft.assertThat(product.getWeight()).as("product.weight").isNotNull().isPositive();
        soft.assertThat(product.getDescription()).as("product.name").isNotNull().isNotBlank();
        soft.assertThat(product.getStatus()).as("product.status").isNotNull().isInstanceOf(ProductStatus.class);
        soft.assertThat(product.getImages()).as("product.images").isNotNull().hasSizeGreaterThanOrEqualTo(0);

        soft.assertAll();
    }

    @Test
    void shouldUpdateProduct() {
        String name = "Updated Product";
        String desc = "Update Product. Console de última geração com 1TB de armazenamento e resolução 4K. Inclui dois controles sem fio e assinatura premium por 3 meses.";
        BigDecimal price = BigDecimal.valueOf(849.99);
        Long weight = 300L;
        ProductStatus status = ProductStatus.UNAVAILABLE;
        Integer amount = 5;

        Product product = generateProductWithMaxImages();
        Product update = generateProduct(name, desc, price, weight, status, amount);

        product.updateProduct(update);

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(product.getId()).as("product.id").isEqualTo(product.getId());
        soft.assertThat(product.getAmount()).as("product.amount").isEqualTo(amount);
        soft.assertThat(product.getPrice()).as("product.price").isEqualTo(price);
        soft.assertThat(product.getName()).as("product.name").isEqualTo(name);
        soft.assertThat(product.getWeight()).as("product.weight").isEqualTo(weight);
        soft.assertThat(product.getDescription()).as("product.name").isEqualTo(desc);
        soft.assertThat(product.getStatus()).as("product.status").isEqualTo(status);

        soft.assertAll();
    }

    @Nested
    class RemoveImage {
        @Test
        void shouldRemoveImage() {
            Product product = generateProductWithMaxImages();
            String name = product.getImages().get(2).getName();

            product.removeImage(name);

            assertThat(product.getImages())
                    .extracting(Image::getIndex)
                    .containsExactly(0, 1, 2, 3);

        }

        @Test
        void shouldThrowException_WhenRemoveImage_ImageNotFound() {
            Product product = generateProductWithMaxImages();
            String name = "name not found";

            assertThatThrownBy(() -> product.removeImage(name))
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Imagem não encontrada");
        }
    }

    @Nested
    class UpdateImageIndex {
        @Test
        void shouldUpdateImageIndex() {
            Product product = generateProductWithMaxImages();

            Map<Integer, String> originalIndices = List.copyOf(product.getImages())
                    .stream()
                    .collect(Collectors.toMap(Image::getIndex, Image::getName));

            List<Image> updateImages = product.getImages().stream().map(i -> {
                if(i.getIndex() == 4) i.setIndex(0);
                else i.setIndex(i.getIndex() + 1);

                return i;
            }).toList();

            product.updateImageIndex(updateImages);


            product.getImages().forEach(updatedImage -> {
                String expectedName;

                if (updatedImage.getIndex() == 0) {
                    expectedName = originalIndices.get(4);
                } else {
                    expectedName = originalIndices.get(updatedImage.getIndex() - 1);
                }

                assertThat(updatedImage.getName()).isEqualTo(expectedName);
            });
        }

        @Test
        void shouldThrowException_WhenUpdateImageIndex_ListIsEmpty() {
            Product product = generateProductWithMaxImages();

            List<Image> images = List.of();

            assertThatThrownBy(() -> product.updateImageIndex(images))
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Lista de novas imagens está vazia");
        }
    }

    @Nested
    class ThrowDomainException {
        @Test
        void shouldThrowException_WhenInstantiatingProduct_IdIsNull() {
            assertThatThrownBy(ProductUtil::generateProductWithIdNull)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Id não pode ser nulo");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_ShortName() {
            assertThatThrownBy(ProductUtil::generateProductWithShortName)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Nome precisa estar entre 3 e 100 caracteres");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_LongName() {
            assertThatThrownBy(ProductUtil::generateProductWithLongName)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Nome precisa estar entre 3 e 100 caracteres");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_ShortDescription() {
            assertThatThrownBy(ProductUtil::generateProductWithShortDescription)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Descrição precisa ter no mínimo 30 caracteres");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_NegativePrice() {
            assertThatThrownBy(ProductUtil::generateProductWithNegativePrice)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Preço precisa ser maior que 0");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_PriceIsEqualToZero() {
            assertThatThrownBy(ProductUtil::generateProductWithZeroPrice)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Preço precisa ser maior que 0");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_WeightIsLessThanOrEqualToZero() {
            assertThatThrownBy(ProductUtil::generateProductWithZeroWeight)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Peso precisa ser maior que 0");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_NegativeWeight() {
            assertThatThrownBy(ProductUtil::generateProductWithZeroWeight)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Peso precisa ser maior que 0");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_NegativeAmount() {
            assertThatThrownBy(ProductUtil::generateProductWithNegativeAmount)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Quantidade precisa ser maior que 0");
        }

        @Test
        void shouldThrowException_WhenInstantiatingProduct_StatusIsNull() {
            assertThatThrownBy(ProductUtil::generateProductWithNullStatus)
                    .isNotNull()
                    .isInstanceOf(DomainValidationException.class)
                    .hasMessage("Status não pode ser nulo");
        }
    }
}
