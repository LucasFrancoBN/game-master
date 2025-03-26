package io.github.lucasfrancobn.gamemaster.application.gateway;

import io.github.lucasfrancobn.gamemaster.domain.entities.Product;

public interface ProductRepository {
    void save(Product product);
}
