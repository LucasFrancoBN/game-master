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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileCleanupServiceImpl implements FileCleanupService {

    @Value("${app.upload.dir}")
    private String uploadDir;

    @Override
    public void cleanOrphanFiles(List<String> filenames) {
        log.info("Starting clean orphan files process");
        Path uploadPath = Paths.get(uploadDir);
        try(Stream<Path> paths = Files.walk(uploadPath)) {
            log.info("Walking through by files at directory {}", uploadDir);
            paths
                .filter(Files::isRegularFile)
                .filter(path -> !filenames.contains(path.getFileName()))
                .forEach(this::safeDelete);
        } catch (Exception e) {
            log.error("An error occurred while traversing the list of files in the specified directory: {}", e.getMessage());
        }
    }
        

    private void safeDelete(Path path) {
        try {
            log.info("Deleting file {}", path.getFileName());
            Files.delete(path);
            log.debug("File deleted successfully");
        } catch (Exception e) {
            log.error("An error occurred while deleting the file from the directory: {}", e.getMessage());
        }
    }
}
