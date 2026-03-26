package com.logincontroller.filmrentalsystem.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    // Root route — redirects based on role after Basic Auth succeeds
    @GetMapping("/")
    public String root(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            // Spring Security will intercept and trigger Basic Auth popup
            return "redirect:/films";
        }

        boolean isManager = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equals("ROLE_MANAGER"));

        // Managers go to dashboard, staff go to films list
        return isManager
                ? "redirect:/dashboard"
                : "redirect:/films";
    }

    // Welcome/home page shown after authentication
    // Passes the username and role into the Thymeleaf model
    @GetMapping("/home")
    public String home(Authentication authentication, Model model) {
        String username = authentication.getName();

        String role = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("UNKNOWN");

        model.addAttribute("username", username);
        model.addAttribute("role", role);
        model.addAttribute("isManager", role.equals("ROLE_MANAGER"));

        return "home"; // resolves to templates/home.html
    }
}