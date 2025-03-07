package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ClientEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ClientRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class ClientRepositoryImpl implements ClientRepository {
    private final ClientRepositoryJpa repositoryJpa;

    @Override
    public Client save(Client client) {
        ClientEntity saved = repositoryJpa.save(ClientMapper.toEntity(client));
        return ClientMapper.toDomain(saved);
    }

    @Override
    public boolean existsByClientId(String clientId) {
        return repositoryJpa.existsByClientId(clientId);
    }

    @Override
    public List<Client> getClients() {
        return repositoryJpa.findAll().stream().map(ClientMapper::toDomain).toList();
    }

    @Override
    public Optional<Client> getClientById(UUID id) {
        return repositoryJpa
                .findById(id)
                .map(ClientMapper::toDomain);
    }

    @Override
    public void deleteClient(Client client) {
        repositoryJpa.delete(ClientMapper.toEntity(client));
    }

    @Override
    public Optional<Client> getClientByClientId(String clientId) {
        return repositoryJpa.findByClientId(clientId)
                .map(ClientMapper::toDomain);
    }

    @Override
    public Optional<Client> findById(UUID id) {
        return repositoryJpa.findById(id).map(ClientMapper::toDomain);
    }
}
