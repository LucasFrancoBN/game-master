package io.github.lucasfrancobn.gamemaster.domain.services;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;

import java.util.List;

public interface StorageService {
    List<Image> uploadAllFiles(List<byte[]> contents, List<String> fileNames);
}
