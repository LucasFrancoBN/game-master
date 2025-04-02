package io.github.lucasfrancobn.gamemaster.application.usecase.client;

import io.github.lucasfrancobn.gamemaster.application.exception.client.ClientNotFoundException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

public class GetClientByClientId {
    private final ClientRepository repository;

    public GetClientByClientId(ClientRepository repository) {
        this.repository = repository;
    }

    public Client get(String clientId) {
        if(clientId == null || clientId.isBlank())
            throw new IllegalArgumentException("Client id não pode ser nulo.");

        return repository.getClientByClientId(clientId)
                .orElseThrow(() -> new ClientNotFoundException("Client não encontrado."));
    }
}
