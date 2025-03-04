package io.github.lucasfrancobn.gamemaster.infra.persistence.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "gm_client")
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false, name = "client_id")
    private String clientId;
    @Column(nullable = false, name = "client_secret")
    private String clientSecret;
    @Column(nullable = false, name = "redirect_uri")
    private String redirectUri;
    @Column(nullable = false)
    private String scope;
}
