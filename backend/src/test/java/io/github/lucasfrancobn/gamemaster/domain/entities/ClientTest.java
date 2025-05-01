package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;
import io.github.lucasfrancobn.gamemaster.utils.ClientUtil;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static io.github.lucasfrancobn.gamemaster.utils.ClientUtil.generateClient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClientTest {
    @Test
    void shouldInstantiateClient() {
        Client client = generateClient();

        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(client).as("client").isNotNull().isInstanceOf(Client.class);
        softly.assertThat(client.getId()).as("client.id").isNotNull();
        softly.assertThat(client.getClientId()).as("client.clientId").isNotBlank();
        softly.assertThat(client.getClientSecret()).as("client.clientSecret").isNotBlank();
        softly.assertThat(client.getRedirectUri()).as("client.redirectUri").isNotBlank();
        softly.assertThat(client.getScope()).as("client.scope").isNotBlank();

        softly.assertAll();
    }

    @Test
    void shouldThrowException_WhenInstantiatingClient_IdIsNull() {
        assertThatThrownBy(ClientUtil::generateClientWithNullId)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("id não pode ser nulo.");
    }

    @Test
    void shouldThrowException_WhenInstantiatingClient_ClientIdIsBlank() {
        assertThatThrownBy(ClientUtil::generateClientWithBlankClientId)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("client id não pode ser vazio");
    }

    @Test
    void shouldThrowException_WhenInstantiatingClient_ClientSecretIsBlank() {
        assertThatThrownBy(ClientUtil::generateClientWithBlankClientSecret)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("client secret não pode ser vazio");
    }

    @Test
    void shouldThrowException_WhenInstantiatingClient_InvalidRedirectUri() {
        assertThatThrownBy(ClientUtil::generateClientWithInvalidRedirectUri)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("redirect uri não é válida");
    }

    @Test
    void shouldThrowException_WhenInstantiatingClient_ScopeIsBlank() {
        assertThatThrownBy(ClientUtil::generateClientWithBlankScope)
                .isNotNull()
                .isInstanceOf(DomainValidationException.class)
                .hasMessage("scope não pode estar vazio");
    }
}
