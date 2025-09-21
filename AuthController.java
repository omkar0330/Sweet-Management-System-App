package com.example.sweetshop.controller;


import org.springframework.web.bind.annotation.*;
import com.example.sweetshop.dto.*;
import com.example.sweetshop.entity.User;
import com.example.sweetshop.repository.UserRepository;
import com.example.sweetshop.config.JwtUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final UserRepository userRepo;
    private final JwtUtils jwtUtils = new JwtUtils();
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public AuthController(UserRepository userRepo) { this.userRepo = userRepo; }


    @PostMapping("/register")
    public AuthResponse register(@RequestBody AuthRequest req) {
        if (userRepo.findByUsername(req.getUsername()).isPresent()) {
            throw new RuntimeException("Username exists");
        }
        User u = new User();
        u.setUsername(req.getUsernam