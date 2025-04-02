package io.github.lucasfrancobn.gamemaster.application.usecase.client;

import io.github.lucasfrancobn.gamemaster.application.exception.client.ClientAlreadyExistsException;
import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

public class CreateClient {
    private final ClientRepository repository;

    public CreateClient(ClientRepository repository) {
        this.repository = repository;
    }

    public Client create(Client client) {
        if (repository.existsByClientId(client.getClientId())) {
            throw new ClientAlreadyExistsException("Cliente com id " + client.getClientId() + " já existe.");
        }

        return repository.save(client);
    }
}
