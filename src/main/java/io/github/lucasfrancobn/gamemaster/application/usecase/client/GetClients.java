package io.github.lucasfrancobn.gamemaster.application.usecase.client;

import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

import java.util.List;

public class GetClients {
    private final ClientRepository repository;

    public GetClients(ClientRepository repository) {
        this.repository = repository;
    }

    public List<Client> get() {
        return repository.getClients();
    }
}
