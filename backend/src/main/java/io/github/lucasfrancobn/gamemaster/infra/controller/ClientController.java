package io.github.lucasfrancobn.gamemaster.infra.controller;

import io.github.lucasfrancobn.gamemaster.application.usecase.client.*;
import io.github.lucasfrancobn.gamemaster.domain.entities.Client;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request.CreateClientRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.dtos.client.request.UpdateClientRequest;
import io.github.lucasfrancobn.gamemaster.infra.presentation.mappers.ClientMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gamemaster/api/v1/clients")
@Slf4j
public class ClientController {
    private final CreateClient createClient;
    private final DeleteClient deleteClient;
    private final UpdateClient updateClient;
    private final GetClientByClientId getClientByClientId;
    private final GetClients getClients;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> createClient(@Valid @RequestBody CreateClientRequest request) {
        log.info("Starting create client process. Data: {}", request);
        Client client = createClient.create(ClientMapper.toDomain(request));

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(client.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        log.info("Starting delete client process. Id: {}", id);
        deleteClient.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateClient(@PathVariable UUID id, @RequestBody UpdateClientRequest request) {
        log.info("Starting update client process. Id: {} | Data: {}", id, request);
        updateClient.update(id, request.clientSecret(), request.scope());

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Client>> getClients() {
        log.info("Starting list clients process.");
        return ResponseEntity.ok(getClients.get());
    }

    @GetMapping("/{clientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> getClient(@PathVariable String clientId) {
        log.info("Starting get client by clientid process. ClientId: {}", clientId);
        return ResponseEntity.ok(getClientByClientId.get(clientId));
    }
}
