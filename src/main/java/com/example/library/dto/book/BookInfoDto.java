package com.example.library.dto.book;

import com.example.library.entity.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class BookInfoDto {
    private long id;
    private String name;
    private Genre genre;
    private String author;
}
