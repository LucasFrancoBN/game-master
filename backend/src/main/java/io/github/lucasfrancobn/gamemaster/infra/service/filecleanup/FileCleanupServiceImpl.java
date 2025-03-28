package io.github.lucasfrancobn.gamemaster.infra.service.filecleanup;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileCleanupServiceImpl implements FileCleanupService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void cleanOrphanFiles(List<String> filenames) {
        Path uploadPath = Paths.get(uploadDir);
        try(Stream<Path> paths = Files.walk(uploadPath)) {
            paths
                .filter(Files::isRegularFile)
                .filter(path -> !filenames.contains(path.getFileName()))
                .forEach(this::safeDelete);
        } catch (Exception e) {
            // TODO - adicionar log caso a exceção aconteça
        }
    }
        

    private void safeDelete(Path path) {
        try {
            Files.delete(path);
        } catch (Exception e) {
            // TODO - Colocar gerenciamento de logs
        }
    }
}
