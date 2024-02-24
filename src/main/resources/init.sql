CREATE SCHEMA library;
SET search_path TO library;

CREATE DATABASE library;

\c library

CREATE TABLE IF NOT EXISTS users
(
    user_id  SERIAL PRIMARY KEY,
    email    VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(25),
    UNIQUE (email, password)
);

CREATE TABLE IF NOT EXISTS books
(
    id    SERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL
);

INSERT INTO books (name, genre)
VALUES ('The Hobbit', 'FANTASY'),
       ('Dune', 'SCIENCE_FICTION'),
       ('Pride and Prejudice', 'ROMANCE'),
       ('The Da Vinci Code', 'MYSTERY'),
       ('The Shining', 'HORROR');
