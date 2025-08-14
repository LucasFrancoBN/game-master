package io.github.lucasfrancobn.gamemaster.application.maintenance;

import io.github.lucasfrancobn.gamemaster.application.gateway.ProductRepository;
import io.github.lucasfrancobn.gamemaster.domain.services.FileCleanupService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static io.github.lucasfrancobn.gamemaster.utils.ImageUtil.generateImage;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

public class CleanOrphanFilesTest {
    @Mock
    private ProductRepository repository;

    @Mock
    private FileCleanupService fileCleanupService;

    @InjectMocks
    private CleanOrphanFiles cleanOrphanFiles;

    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    void shouldCleanOrphanFiles() {
        var listImages = List.of(generateImage(0), generateImage(1), generateImage(2));

        when(repository.findAllImages()).thenReturn(listImages);
        doNothing().when(fileCleanupService).cleanOrphanFiles(anyList());

        cleanOrphanFiles.clean();

        verify(repository, times(1)).findAllImages();
        verify(fileCleanupService, times(1)).cleanOrphanFiles(anyList());
    }
}
