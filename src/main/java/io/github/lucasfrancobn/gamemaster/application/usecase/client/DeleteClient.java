package io.github.lucasfrancobn.gamemaster.application.usecase.client;

import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

import java.util.UUID;

public class DeleteClient {
    private ClientRepository repository;

    public DeleteClient(ClientRepository repository) {
        this.repository = repository;
    }

    public void delete(UUID id) {
        Client client = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        repository.deleteClient(client);
    }
}
