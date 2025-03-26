
package io.github.lucasfrancobn.gamemaster.domain.services;

import java.util.List;

public interface FileCleanupService {
    void cleanOrphanFiles(List<String> filename);
}
