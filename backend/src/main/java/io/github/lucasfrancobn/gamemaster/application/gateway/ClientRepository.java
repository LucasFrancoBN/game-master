package io.github.lucasfrancobn.gamemaster.application.gateway;

import io.github.lucasfrancobn.gamemaster.domain.entities.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);

    boolean existsByClientId(String clientId);

    List<Client> getClients();
    
    Optional<Client> getClientById(UUID id);

    Optional<Client> getClientByClientId(String clientId);

    void deleteClient(Client client);
}
