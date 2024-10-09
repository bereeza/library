package com.example.library.service;

import com.example.library.dto.user.UserSaveDto;
import com.example.library.entity.User;
import com.example.library.entity.enums.Role;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.exception.UserAlreadyExistException;
import com.example.library.jwt.JwtProvider;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public String saveUserByCredentials(UserSaveDto user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists");
        }

        userRepository.save(buildUser(user));
        return jwtProvider.createToken(user.getEmail());
    }

    public String findUserByCredentials(UserSaveDto user) {
        User existingUser = userRepository.findUserByEmail(user.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User does not exist."));

        if (passwordEncoder.matches(user.getPassword(), existingUser.getPassword())) {
            return jwtProvider.createToken(existingUser.getEmail());
        }

        throw new BadCredentialsException("Invalid credentials");
    }

    private User buildUser(UserSaveDto user) {
        return User.builder()
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(Role.USER)
                .build();
    }
}
