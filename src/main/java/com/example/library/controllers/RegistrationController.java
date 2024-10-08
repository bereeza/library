package com.example.library.controllers;

import com.example.library.dto.user.UserSaveDto;
import com.example.library.dto.token.TokenResponse;
import com.example.library.exception.ErrorResponse;
import com.example.library.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<TokenResponse> signUp(@RequestBody UserSaveDto user) {
        String token = userService.saveUserByCredentials(user);
        return ResponseEntity.ok(TokenResponse.builder()
                .token(token)
                .build());
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenResponse> signIn(@RequestBody UserSaveDto user) {
        String token = userService.findUserByCredentials(user);
        return ResponseEntity.ok(TokenResponse.builder()
                .token(token)
                .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialException(BadCredentialsException ex) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Bad credentials")
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}
