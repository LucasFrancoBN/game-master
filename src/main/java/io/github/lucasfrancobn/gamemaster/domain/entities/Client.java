package io.github.lucasfrancobn.gamemaster.domain.entities;

import io.github.lucasfrancobn.gamemaster.domain.entities.validation.client.RedirectUriValidator;

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
            throw new IllegalArgumentException("id cannot be null");
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
            throw new IllegalArgumentException("client id cannot be empty");
        }

        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        if(clientSecret == null || clientSecret.isBlank()) {
            throw new IllegalArgumentException("client secret cannot be empty");
        }
        this.clientSecret = clientSecret;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        if(!RedirectUriValidator.isValid(redirectUri)) {
            throw new IllegalArgumentException("redirect uri is not valid");
        }

        this.redirectUri = redirectUri;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        if(scope == null || scope.isBlank()) {
            throw new IllegalArgumentException("scope cannot be empty");
        }

        this.scope = scope;
    }
}
