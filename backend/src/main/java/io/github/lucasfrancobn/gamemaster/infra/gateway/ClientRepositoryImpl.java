package io.github.lucasfrancobn.gamemaster.infra.gateway;

import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;
import io.github.lucasfrancobn.gamemaster.infra.persistence.model.ClientEntity;
import io.github.lucasfrancobn.gamemaster.infra.persistence.repository.ClientRepositoryJpa;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ClientMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
@Slf4j
public class ClientRepositoryImpl implements ClientRepository {
    private final ClientRepositoryJpa repositoryJpa;

    @Override
    @Transactional
    public Client save(Client client) {
        log.trace("Saving client with id {}", client.getClientId());
        ClientEntity saved = repositoryJpa.save(ClientMapper.toEntity(client));
        log.debug("Client saved with id {}", saved.getClientId());
        return ClientMapper.toDomain(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByClientId(String clientId) {
        boolean exists = repositoryJpa.existsByClientId(clientId);
        log.trace("exists client with client id {}: {}", clientId, exists);
        return exists;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getClients() {
        log.debug("Getting all clients");
        return repositoryJpa.findAll().stream().map(ClientMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getClientById(UUID id) {
        log.debug("Getting Client by id {}", id);
        return repositoryJpa
                .findById(id)
                .map(ClientMapper::toDomain);
    }

    @Override
    @Transactional
    public void deleteClient(Client client) {
        log.trace("Deleting client with id {}", client.getClientId());
        repositoryJpa.delete(ClientMapper.toEntity(client));
        log.debug("Client deleted");
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> getClientByClientId(String clientId) {
        log.debug("Getting client with id {}", clientId);
        return repositoryJpa.findByClientId(clientId)
                .map(ClientMapper::toDomain);
    }
}
