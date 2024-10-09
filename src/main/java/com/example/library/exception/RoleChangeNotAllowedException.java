package com.example.library.exception;

public class RoleChangeNotAllowedException extends RuntimeException {
    public RoleChangeNotAllowedException() {
    }

    public RoleChangeNotAllowedException(String message) {
        super(message);
    }
}
