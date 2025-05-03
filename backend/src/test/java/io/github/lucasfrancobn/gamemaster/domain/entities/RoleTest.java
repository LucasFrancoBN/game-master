package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;
import io.github.lucasfrancobn.gamemaster.utils.RoleUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.github.lucasfrancobn.gamemaster.utils.RoleUtil.generateRole;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RoleTest {
    @Test
    void shouldInstantiateRole() {
        Role role = generateRole();

        SoftAssertions soft = new SoftAssertions();

        soft.assertThat(role).as("role").isNotNull().isInstanceOf(Role.class);
        soft.assertThat(role.getId()).as("role.id").isNotNull().isInstanceOf(UUID.class);
        soft.assertThat(role.getAuthority()).as("role.authority").isNotNull().isNotBlank();
        soft.assertThat(role.getAuthorityName()).as("role.authorityName").isNotNull().isNotBlank();
        soft.assertThat(role.getDescription()).as("role.description").isNotNull().isNotBlank();

        soft.assertAll();
    }

    @Test
    void shouldThrowException_WhenInstantiatingRole_IdIsNull() {
        assertThatThrownBy(RoleUtil::generateRoleWithNullId)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Id é nulo");
    }

    @Test
    void shouldThrowException_WhenInstantiatingRole_AuthorityNameIsBlank() {
        assertThatThrownBy(RoleUtil::generateRoleWithEmptyAuthorityName)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Nome da autoridade não pode estar vazio.");
    }

    @Test
    void shouldThrowException_WhenInstantiatingRole_AuthorityIsBlank() {
        assertThatThrownBy(RoleUtil::generateRoleWithEmptyAuthority)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("Autoridade não pode estar vazio");
    }
}
