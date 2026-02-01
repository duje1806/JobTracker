package com.example.jobtracker.service;

import com.example.jobtracker.dto.LoginRequest;
import com.example.jobtracker.dto.RegisterRequest;
import com.example.jobtracker.entity.User;
import com.example.jobtracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public User save(User user) {
        return userRepository.save(user);
    }
    public User register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();

        return userRepository.save(user);
    }
    public User login(LoginRequest request){
        return userRepository.findByEmail(request.getEmail())
                .filter(u -> u.getPassword().equals(request.getPassword()))
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));
    }
}
