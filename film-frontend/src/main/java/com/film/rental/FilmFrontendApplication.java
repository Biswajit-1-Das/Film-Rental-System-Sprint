package com.film.rental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.frontend")
public class FilmFrontendApplication {
    public static void main(String[] args) {
        SpringApplication.run(FilmFrontendApplication.class, args);
    }
}