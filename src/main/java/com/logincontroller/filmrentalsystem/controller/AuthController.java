package com.logincontroller.filmrentalsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    // Returns the currently authenticated user's info
    // Useful for verifying who is logged in after Basic Auth succeeds
    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getCurrentUser(
            Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(401).build();
        }

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("UNKNOWN");

        Map<String, String> response = new HashMap<>();
        response.put("username", authentication.getName());
        response.put("role", role);

        return ResponseEntity.ok(response);
    }

    // Simple ping to check if the user is authenticated
    // Returns 200 if credentials are valid, 401 if not (handled by Spring Security)
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("authenticated");
    }
}