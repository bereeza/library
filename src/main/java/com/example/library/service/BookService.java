package com.example.library.service;

import com.example.library.dto.book.BookInfoDto;
import com.example.library.dto.book.BookSaveDto;
import com.example.library.entity.Book;
import com.example.library.entity.User;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.InvalidEmailException;
import com.example.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    private final ProfileService profileService;

    public BookInfoDto getBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(this::buildBookInfo)
                .orElseThrow(() -> new EntityNotFoundException("Book does not exist."));
    }

    public BookInfoDto saveBook(BookSaveDto book) {
        Book savedBook = bookRepository.save(buildBook(book));
        return buildBookInfo(savedBook);
    }

    public void dropBookById(long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book does not exist."));

        String userEmail = profileService.getCurrentUser().getEmail();
        String authorEmail = book.getUser().getEmail();

        if (!userEmail.equals(authorEmail)) {
            throw new InvalidEmailException("You can't drop this book.");
        }

        bookRepository.deleteById(id);
    }


    private Book buildBook(BookSaveDto book) {
        User currentUser = profileService.convertDtoToUser();

        return Book.builder()
                .genre(book.getGenre())
                .name(book.getName())
                .user(currentUser)
                .build();
    }

    private BookInfoDto buildBookInfo(Book book) {
        return BookInfoDto.builder()
                .id(book.getId())
                .name(book.getName())
                .genre(book.getGenre())
                .author(book.getUser().getEmail())
                .build();
    }
}