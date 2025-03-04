package io.github.lucasfrancobn.gamemaster.infra.presentation.mappers;

import io.github.lucasfrancobn.gamemaster.domain.entities.Client;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ClientEntity;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request.CreateClientRequest;

public class ClientMapper {
    public static Client toDomain(ClientEntity entity) {
        return new Client(
                entity.getId(),
                entity.getClientId(),
                entity.getClientSecret(),
                entity.getRedirectUri(),
                entity.getScope()
        );
    }

    public static Client toDomain(CreateClientRequest request) {
        return new Client(
                request.clientId(),
                request.clientSecret(),
                request.redirectUri(),
                request.scope()
        );
    }


    public static ClientEntity toEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setId(client.getId());
        entity.setClientId(client.getClientId());
        entity.setClientSecret(client.getClientSecret());
        entity.setRedirectUri(client.getRedirectUri());
        entity.setScope(client.getScope());

        return entity;
    }
}
