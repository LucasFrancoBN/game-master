package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;
import io.github.lucasfrancobn.gamemaster.utils.UserUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.UserUtil.generateAdminUser;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class UserTest {
    @Test
    void shouldInstantiateUser() {
        User user = generateAdminUser();

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(user).as("user").isNotNull().isInstanceOf(User.class);
        soft.assertThat(user.getId()).as("user.id").isNotNull().isInstanceOf(UUID.class);
        soft.assertThat(user.getEmail()).as("user.email").isNotNull().isNotBlank();
        soft.assertThat(user.getPassword()).as("user.password").isNotNull().isNotBlank();
        soft.assertThat(user.getRoles()).as("user.roles").isNotNull().hasSizeGreaterThanOrEqualTo(1);

        soft.assertAll();
    }

    @Test
    void shouldThrowException_WhenInstantiatingUser_IdIsNull() {
        assertThatThrownBy(UserUtil::generateUserWithNullId)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Id não pode estar vazio");
    }

    @Test
    void shouldThrowException_WhenInstantiatingUser_InvalidEmail() {
        assertThatThrownBy(UserUtil::generateUserWithInvalidEmail)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("E-mail inválido.");
    }

    @Test
    void shouldThrowException_WhenInstantiatingUser_PasswordIsBlank() {
        assertThatThrownBy(UserUtil::generateUserWithEmptyPassword)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("A senha não pode ser vazia");
    }
}
