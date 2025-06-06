package io.github.lucasfrancobn.gamemaster.application.usecase.client;

import io.github.lucasfrancobn.gamemaster.application.exception.client.ClientNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

import java.util.UUID;

public class UpdateClient {
    private final ClientRepository repository;

    public UpdateClient(ClientRepository repository) {
        this.repository = repository;
    }

    public void update(UUID id, String clientSecret, String scope) {
        Client clientFounded = repository.getClientById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client não encontrado."));

        if (!clientSecret.isBlank()) {
            clientFounded.setClientSecret(clientSecret);
        }

        if (!scope.isBlank()) {
            clientFounded.setScope(scope);
        }

        repository.save(clientFounded);
    }
}
