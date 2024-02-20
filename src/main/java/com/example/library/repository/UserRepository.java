package com.example.library.repository;

import com.example.library.dto.Book;
import com.example.library.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u.favoriteBooks FROM User u WHERE u.id = :userId")
    List<Book> findFavoriteBooksByUserId(@Param("userId") long userId);

    @Query("UPDATE User u SET u.favoriteBooks = :favoriteBooks WHERE u.id = :userId")
    void addFavoriteBooks(@Param("userId") long userId, @Param("favoriteBooks") Set<Book> favoriteBooks);

    @Query("DELETE FROM User u WHERE :bookId MEMBER OF u.favoriteBooks AND u.id = :userId")
    void removeFavoriteBook(@Param("userId") long userId, @Param("bookId") long bookId);
}
