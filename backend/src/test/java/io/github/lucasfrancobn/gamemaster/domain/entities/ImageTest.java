package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;
import io.github.lucasfrancobn.gamemaster.utils.ImageUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static io.github.lucasfrancobn.gamemaster.utils.ImageUtil.generateImage;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageTest {
    @Test
    void shouldInstantiateImage() {
        Image image = generateImage();

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(image).as("image").isNotNull().isInstanceOf(Image.class);
        soft.assertThat(image.getName()).as("image.name").isNotNull().isNotBlank();
        soft.assertThat(image.getIndex()).as("image.index").isNotNull().isGreaterThanOrEqualTo(0);
        soft.assertThat(image.getPath()).as("image.path").isNotNull().isNotBlank();
        soft.assertThat(image.getSize()).as("image.size").isNotNull().isGreaterThanOrEqualTo(0);
        soft.assertThat(image.getType()).as("image.type").isNotNull();
        soft.assertThat(image.getUrl()).as("image.url").isNotNull().isNotBlank();

        soft.assertAll();
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_NameIsBlank() {
        assertThatThrownBy(ImageUtil::generateImageWithEmptyName)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Nome não pode estar vazio ou maior que 255 caracteres");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_NameExceeds255Characters() {
        assertThatThrownBy(ImageUtil::generateImageWithLongName)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Nome não pode estar vazio ou maior que 255 caracteres");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_PathIsBlank() {
        assertThatThrownBy(ImageUtil::generateImageWithEmptyPath)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Path não pode estar vazio ou maior que 500 caracteres");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_InvalidUrl() {
        assertThatThrownBy(ImageUtil::generateImageWithInvalidUrl)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("URL da imagem inválida");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_TypeIsNull() {
        assertThatThrownBy(ImageUtil::generateImageWithNullType)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Tipo da imagem não pode ser nulo");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_SizeIsNegative() {
        assertThatThrownBy(ImageUtil::generateImageWithNegativeSize)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Tamanho deve ser um número positivo");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_InvalidIndex() {
        assertThatThrownBy(ImageUtil::generateImageWithInvalidIndex)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Índice inválido (deve estar entre 0 e 4)");
    }

    @Test
    void shouldThrowException_WhenInstantiatingImage_ProductIsNull() {
        assertThatThrownBy(ImageUtil::generateImageWithNullProduct)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("O produto associado a imagem não pode ser nulo");
    }
}
