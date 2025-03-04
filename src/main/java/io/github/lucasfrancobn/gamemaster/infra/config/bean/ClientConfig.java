package io.github.lucasfrancobn.gamemaster.infra.config.bean;

import io.github.lucasfrancobn.gamemaster.application.gateway.ClientRepository;
import io.github.lucasfrancobn.gamemaster.application.usecase.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {
    @Bean
    public CreateClient createClient(ClientRepository clientRepository) {
        return new CreateClient(clientRepository);
    }

    @Bean
    public GetClients getClients(ClientRepository clientRepository) {
        return new GetClients(clientRepository);
    }

    @Bean
    public UpdateClient updateClient(ClientRepository clientRepository) {
        return new UpdateClient(clientRepository);
    }

    @Bean
    public DeleteClient deleteClient(ClientRepository clientRepository) {
        return new DeleteClient(clientRepository);
    }

    @Bean
    public GetClientByClientId getClientByClientId(ClientRepository clientRepository) {
        return new GetClientByClientId(clientRepository);
    }
}
