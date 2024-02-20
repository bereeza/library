CREATE SCHEMA library;
SET search_path TO library;

CREATE DATABASE library;

\c library

CREATE TABLE IF NOT EXISTS book
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS book_genres
(
    book_id SERIAL,
    genre   VARCHAR(255),
    PRIMARY KEY (book_id, genre),
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE IF NOT EXISTS book_authors
(
    book_id SERIAL,
    author  VARCHAR(255),
    PRIMARY KEY (book_id, author),
    FOREIGN KEY (book_id) REFERENCES book (id)
);

CREATE TABLE IF NOT EXISTS book_user
(
    id       SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_favorite_books
(
    user_id SERIAL,
    book_id SERIAL,
    PRIMARY KEY (user_id, book_id),
    FOREIGN KEY (user_id) REFERENCES book_user (id),
    FOREIGN KEY (book_id) REFERENCES book (id)
);
