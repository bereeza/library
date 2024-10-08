package com.example.library.dto.book;

import com.example.library.entity.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class BookSaveDto {
    private String name;
    private Genre genre;
}
