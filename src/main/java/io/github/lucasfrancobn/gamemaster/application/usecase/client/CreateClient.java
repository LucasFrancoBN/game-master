package io.github.lucasfrancobn.gamemaster.application.usecase.client;

import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

public class CreateClient {
    private final ClientRepository repository;

    public CreateClient(ClientRepository repository) {
        this.repository = repository;
    }

    public Client create(Client client) {
        if (repository.existsByClientId(client.getClientId())) {
            throw new IllegalArgumentException("Client with id " + client.getClientId() + " already exists");
        }

        return repository.save(client);
    }
}
