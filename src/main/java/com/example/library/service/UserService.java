package com.example.library.service;

import com.example.library.configuration.CustomerUserDetails;
import com.example.library.configuration.UserHolder;
import com.example.library.entity.User;
import com.example.library.repository.UserRepository;
import lombok.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final UserHolder userHolder;

    public UserService(UserRepository userRepository, UserHolder userHolder) {
        this.userRepository = userRepository;
        this.userHolder = userHolder;
    }

    public User addUser(@NonNull User entity) {
        return userRepository.save(entity);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new CustomerUserDetails(user.get());
    }
}
