package com.example.library.exception;

public class UserAlreadyExistException extends EntityNotFoundException {
    public UserAlreadyExistException() {}

    public UserAlreadyExistException(String message) {
        super(message);
    }
}
