package io.github.lucasfrancobn.gamemaster.infra.persistence.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.UUID;

@Data
@Entity
@Table(name = "gm_role")
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String authorityName;
    @Column(unique = true, nullable = false)
    private String authority;
    private String description;

    @Override
    public String getAuthority() {
        return authority;
    }
}
