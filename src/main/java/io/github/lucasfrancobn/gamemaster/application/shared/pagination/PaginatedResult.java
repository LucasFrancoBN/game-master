package io.github.lucasfrancobn.gamemaster.application.shared.pagination;

import java.util.List;

public class PaginatedResult<T> {
    private List<T> content;
    private int currentPage;
    private int pageSize;
    private long totalElements;

    public PaginatedResult(List<T> content, int currentPage, int pageSize, long totalElements) {
        this.content = content;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.totalElements = totalElements;
    }

    public List<T> getContent() {
        return content;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public long getTotalElements() {
        return totalElements;
    }
}
