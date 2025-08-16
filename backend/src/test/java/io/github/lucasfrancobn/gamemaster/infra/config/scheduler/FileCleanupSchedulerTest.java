package io.github.lucasfrancobn.gamemaster.infra.config.scheduler;

import io.github.lucasfrancobn.gamemaster.application.maintenance.CleanOrphanFiles;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

public class FileCleanupSchedulerTest {
    @Mock
    private CleanOrphanFiles cleanOrphanFiles;

    @InjectMocks
    private FileCleanupScheduler fileCleanupScheduler;

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
    void shouldExecuteScheduleFileCleanup() {
        doNothing().when(cleanOrphanFiles).clean();

        fileCleanupScheduler.scheduleFileCleanup();

        verify(cleanOrphanFiles, times(1)).clean();
    }
}
