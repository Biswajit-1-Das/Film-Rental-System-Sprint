package com.logincontroller.filmrentalsystem.config;

import com.logincontroller.filmrentalsystem.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final StaffService staffService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // publicly accessible
                        .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                        // manager-only routes
                        .requestMatchers("/dashboard/**", "/stores/**", "/staff/**")
                        .hasRole("MANAGER")
                        // all other routes require any authenticated user
                        .anyRequest().authenticated()
                )
                // use HTTP Basic Auth — browser will show the native login popup
                .httpBasic(basic -> {})
                // disable CSRF for simplicity with basic auth
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            HttpSecurity http) throws Exception {

        AuthenticationManagerBuilder builder =
                http.getSharedObject(AuthenticationManagerBuilder.class);

        builder
                .userDetailsService(staffService)
                .passwordEncoder(passwordEncoder());

        return builder.build();
    }
}