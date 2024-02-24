package com.example.library.controllers;

import com.example.library.configuration.UserHolder;
import com.example.library.entity.User;
import com.example.library.service.UserService;
import com.example.library.utils.PasswordManger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/")
public class RegistrationController {
    private final UserService userService;
    private final UserHolder userHolder;

    public RegistrationController(UserService userService, UserHolder userHolder) {
        this.userService = userService;
        this.userHolder = userHolder;
    }

    @PostMapping
    public ResponseEntity<User> signUp(@RequestBody User user) {
        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }

        User registeredUser = userService.addUser(user);
        userHolder.setCurrentUser(registeredUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @GetMapping("/sign")
    public ResponseEntity<String> signIn(@RequestParam String email, @RequestParam String password) {
        Optional<User> userOptional = userService.findUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (PasswordManger.checkPassword(password, user.getPassword())) {
                userHolder.setCurrentUser(user);
                return ResponseEntity.status(HttpStatus.OK).body("User signed in successfully");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
