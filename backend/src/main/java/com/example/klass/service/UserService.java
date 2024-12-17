package com.example.klass.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.klass.model.Users;
import com.example.klass.repository.UserRepo;


@Service
public class UserService {
    @Autowired
    private UserRepo repo; 
    
    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public Users register(Users user) {
        try {
            user.setPassword(encoder.encode(user.getPassword())); // Encrypt password
            return repo.save(user); // Save user to database
        } catch (Exception e) { 
            throw new RuntimeException("Error saving user: " + e.getMessage(), e);
        }
    }

    public String verify(Users user) {
        Authentication authentication =
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "Login failed";
        }
    }
}
