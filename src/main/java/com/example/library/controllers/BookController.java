package com.example.library.controllers;

import com.example.library.dto.Response;
import com.example.library.dto.book.BookInfoDto;
import com.example.library.dto.book.BookSaveDto;
import com.example.library.dto.pagination.PaginationResponse;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookInfoDto> getBookById(@PathVariable long id) {
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping
    public ResponseEntity<PaginationResponse<BookInfoDto>> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {

        return ResponseEntity.ok(bookService.getBooks(page, size));
    }

    @PostMapping("/add-book")
    public ResponseEntity<BookInfoDto> addBook(@RequestBody BookSaveDto book) {
        return ResponseEntity.ok(bookService.saveBook(book));
    }

    @PostMapping("/{id}")
    public ResponseEntity<BookInfoDto> updateBook(@PathVariable long id, @RequestBody BookSaveDto book) {
        return ResponseEntity.ok(bookService.updateBookById(id, book));
    }

    @DeleteMapping("/drop-book/{id}")
    public Response dropBookById(@PathVariable long id) {
        bookService.dropBookById(id);
        return Response.builder()
                .status(HttpStatus.NO_CONTENT)
                .message("The book has been deleted.")
                .build();
    }
}