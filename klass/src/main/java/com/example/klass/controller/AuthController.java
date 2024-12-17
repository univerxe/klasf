package com.example.klass.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.klass.utils.Scrap;

import jakarta.servlet.http.HttpServletRequest;
 

// response = json or xml
@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    // @Autowired
    // private UserService userService;

    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return request.getSession().getId();
    }

    @GetMapping("/news")
    public ArrayList<HashMap<String, String>> greet() throws java.io.IOException {
        return Scrap.fetchNews();
    }



    // @CrossOrigin(origins = "*")

    // // Login endpoint - Returns JWT token upon success
    // @PostMapping("/login")
    // public ResponseEntity<?> processLogin(@RequestParam String username,
    //                                       @RequestParam String password) {
    //     if ("a".equals(username) && "pw".equals(password)) {
    //         // Authenticate user (placeholder service call)
    //         userService.authenticate(username, password);

    //         // Generate JWT token
    //         String token = JwtUtil.generateToken(username);
    //         return ResponseEntity.ok().body("Bearer " + token);
    //     } else {
    //         return ResponseEntity.status(401).body("Invalid username or password");
    //     }
    // }

    // // Home endpoint - Requires valid JWT token
    // @GetMapping("/home")
    // public ResponseEntity<?> home(@RequestHeader("Authorization") String authHeader) {
    //     try {
    //         // Extract and validate JWT
    //         String token = authHeader.replace("Bearer ", "");
    //         Claims claims = JwtUtil.validateToken(token);

    //         // Retrieve username from token claims
    //         String username = claims.getSubject();
    //         return ResponseEntity.ok().body("Welcome, " + username + "!");
    //     } catch (Exception e) {
    //         return ResponseEntity.status(401).body("Invalid or expired token");
    //     }
    // }
}
