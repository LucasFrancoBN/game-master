package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.validation.client.RedirectUriValidator;
import io.github.lucasfrancobn.gamemaster.domain.exception.DomainValidationException;

import java.util.UUID;

public class Client {
    private UUID id;
    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String scope;

    public Client() {
    }

    public Client(UUID id, String clientId, String clientSecret, String redirectUri, String scope) {
        if(id == null) {
            throw new DomainValidationException("id não pode ser nulo.");
        }
        this.id = id;

        setClientId(clientId);
        setClientSecret(clientSecret);
        setRedirectUri(redirectUri);
        setScope(scope);
    }

    public Client(String clientId, String clientSecret, String redirectUri, String scope) {
        setClientId(clientId);
        setClientSecret(clientSecret);
        setRedirectUri(redirectUri);
        setScope(scope);
    }

    public UUID getId() {
        return id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        if(clientId == null || clientId.isBlank()) {
            throw new DomainValidationException("client id não pode ser vazio");
        }

        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        if(clientSecret == null || clientSecret.isBlank()) {
            throw new DomainValidationException("client secret não pode ser vazio");
        }
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        if(!RedirectUriValidator.isValid(redirectUri)) {
            throw new DomainValidationException("redirect uri não é válida");
        }

        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        if(scope == null || scope.isBlank()) {
            throw new DomainValidationException("scope não pode estar vazio");
        }

        this.scope = scope;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientId='" + clientId + '\'' +
                ", clientSecret='" + clientSecret + '\'' +
                ", redirectUri='" + redirectUri + '\'' +
                ", scope='" + scope + '\'' +
                '}';
    }
}
