package com.example.library.dto.pagination;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
public class PaginationResponse<T> {
    private long size;
    private long page;
    private List<T> items;
}
