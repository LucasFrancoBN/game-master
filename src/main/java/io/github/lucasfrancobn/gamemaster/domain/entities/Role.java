package io.github.lucasfrancobn.gamemaster.domain.entities;

import java.util.UUID;

public class Role {
    private UUID id;
    private String authorityName;
    private String authority;
    private String description;

    public Role() {
    }

    public Role(UUID id, String authorityName, String authority, String description) {
        if(id == null) {
            throw new NullPointerException("id is null");
        }
        this.id = id;
        this.authorityName = authorityName;
        this.authority = authority;
        this.description = description;
    }

    public Role(String authorityName, String authority, String description) {
        setAuthorityName(authorityName);
        setAuthority(authority);
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        if(authorityName == null || authorityName.isBlank()) {
            throw new IllegalArgumentException("Authority name cannot be blank.");
        }
        this.authorityName = authorityName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        if(authority == null || authority.isBlank()) {
            throw new IllegalArgumentException("Authority cannot be blank.");
        }
        this.authority = authority.toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
