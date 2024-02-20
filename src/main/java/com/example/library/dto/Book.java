package com.example.library.dto;

import com.example.library.dto.enums.Genre;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @ElementCollection
    @CollectionTable(
            name = "book_genres",
            joinColumns = @JoinColumn(name = "book_id")
    )
    private Set<Genre> genres;

    @ElementCollection
    @CollectionTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id")
    )
    private Set<String> authors;

    public Book() {}
}