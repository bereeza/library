package com.example.library.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {}
    public EntityNotFoundException(String message) {
        super(message);
    }
}
