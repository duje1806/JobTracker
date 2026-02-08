package com.example.jobtracker.controller;
import com.example.jobtracker.dto.LoginRequest;
import com.example.jobtracker.dto.LoginResponse;
import com.example.jobtracker.dto.RegisterRequest;
import com.example.jobtracker.entity.User;
import com.example.jobtracker.service.JwtService;
import com.example.jobtracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public User register(@Valid @RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        User user = userService.login(request);
        String token = jwtService.generateToken(user.getEmail()); // 👈 OVDJE
        return new LoginResponse(token);
    }


}
