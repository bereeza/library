package com.example.library.service;

import com.example.library.dto.user.UserInfoDto;
import com.example.library.entity.User;
import com.example.library.exception.UserNotFoundException;
import com.example.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final UserRepository userRepository;

    public UserInfoDto getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null && auth.isAuthenticated()) {
            String email = auth.getName();
            Optional<User> user = userRepository.findUserByEmail(email);

            return user.map(this::buildInfoUser)
                    .orElseThrow(() -> new UserNotFoundException("User does not exist."));
        }

        throw new SecurityException("User not authenticated.");
    }

    public User convertDtoToUser() {
        UserInfoDto userInfoDto = this.getCurrentUser();
        return User.builder()
                .id(userInfoDto.getId())
                .email(userInfoDto.getEmail())
                .build();
    }

    private UserInfoDto buildInfoUser(User user) {
        return UserInfoDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}
