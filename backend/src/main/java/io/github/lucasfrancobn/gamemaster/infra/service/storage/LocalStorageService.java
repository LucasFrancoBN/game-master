package io.github.lucasfrancobn.gamemaster.infra.service.storage;

import io.github.lucasfrancobn.gamemaster.domain.entities.Image;
import io.github.lucasfrancobn.gamemaster.domain.entities.enums.ImageType;
import io.github.lucasfrancobn.gamemaster.domain.services.StorageService;
import io.github.lucasfrancobn.gamemaster.infra.exception.storage.CreateDirectoryException;
import io.github.lucasfrancobn.gamemaster.infra.exception.storage.StorageFileException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class LocalStorageService implements StorageService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd-HHmmss-SSS");

    @Value("${app.upload.dir}")
    private String uploadDir;

    @PostConstruct
    public void init() {
        try {
            log.trace("Creating directory {}", uploadDir);
            Files.createDirectories(Paths.get(uploadDir));
        } catch (IOException e) {
            throw new CreateDirectoryException("Não foi possível criar o diretório upload: " + e.getMessage());
        }
    }

    @Override
    public List<Image> uploadAllFiles(List<byte[]> files, List<String> fileNames) {
        log.info("Starting upload all files process");
        return IntStream.range(0, files.size())
                .mapToObj(i -> {
                    String fileName = generateUniqueFileName(fileNames.get(i));
                    Path path = Paths.get(uploadDir, fileName);
                    try {
                        log.info("Creating image {} at directory {}", fileName, uploadDir);
                        Files.write(path, files.get(i));
                        log.debug("File {} created at {}", fileName, uploadDir);
                        return new Image(
                                fileName,
                                path.toAbsolutePath().toString(),
                                ImageType.fromExtension(fileName.split("\\.")[1]),
                                files.get(i).length * 1024 * 1024L
                        );
                    } catch (IOException e) {
                        throw new StorageFileException("Falha ao armazenar o arquivo: " + fileName);
                    }
                })
                .toList();
    }

    private String generateUniqueFileName(String originalName) {
        String extension = StringUtils.getFilenameExtension(originalName);

        String timestamp = LocalDateTime.now().format(FORMATTER);

        String random = String.format("$%04d", new Random().nextInt(10000));
        String uuid = UUID.randomUUID().toString().substring(0,4);

        String baseName = timestamp + "-" + random + "-" + uuid;

        return extension != null ? baseName + "." + extension : baseName;
    }
}
