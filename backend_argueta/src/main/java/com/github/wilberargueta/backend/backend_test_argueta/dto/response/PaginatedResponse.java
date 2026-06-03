package com.github.wilberargueta.backend.backend_test_argueta.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class PaginatedResponse<T> {
    private List<T> data;
    private int currentPage;
    private int totalPages;
    private long totalItems;
    private int pageSize;
    private boolean hasNext;
    private boolean hasPrevious;

}