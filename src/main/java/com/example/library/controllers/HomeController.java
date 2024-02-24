package com.example.library.controllers;

import com.example.library.configuration.UserHolder;
import com.example.library.entity.Book;
import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import com.example.library.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/u")
public class HomeController {
    private final UserRepository userRepository;
    private final BookService bookService;
    private final UserHolder userHolder;

    public HomeController(UserRepository userRepository, BookService bookService, UserHolder userHolder) {
        this.userRepository = userRepository;
        this.bookService = bookService;
        this.userHolder = userHolder;
    }

    @GetMapping
    public String current() {
        return userHolder.getCurrentUser().toString();
    }

    @GetMapping("/fav")
    public ResponseEntity<List<Book>> favoriteBooks() {
        User currentUser = userHolder.getCurrentUser();
        return ResponseEntity.ok(currentUser.getBooks());
    }

    @PostMapping("/add")
    public ResponseEntity<List<Book>> addToFavorite(@RequestParam long bookId) {
        Book book = bookService.getBookById(bookId).orElseThrow();
        User currentUser = userHolder.getCurrentUser();

        if (!currentUser.getBooks().contains(book)) {
            currentUser.getBooks().add(book);
            userRepository.save(currentUser);
            return ResponseEntity.ok(currentUser.getBooks());
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping("/drop")
    public ResponseEntity<Void> dropFavoriteBook(@RequestParam long bookId) {
        Book bookToRemove = bookService.getBookById(bookId).orElseThrow();
        User currentUser = userHolder.getCurrentUser();

        if (currentUser.getBooks().remove(bookToRemove)) {
            userRepository.save(currentUser);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}