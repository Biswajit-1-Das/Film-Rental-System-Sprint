package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/films")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    // Supports: /api/films OR /api/films?title=Matrix OR /api/films?languageId=1
    @GetMapping
    public ResponseEntity<List<Film>> getFilms(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Integer languageId) {
        
        if (title != null) {
            return ResponseEntity.ok(filmService.searchFilmsByTitle(title));
        }
        if (languageId != null) {
            return ResponseEntity.ok(filmService.getFilmsByLanguage(languageId));
        }
        
        return ResponseEntity.ok(filmService.getAllFilms());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        Film createdFilm = filmService.createFilm(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFilm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable Short id, @RequestBody Film film) {
        return ResponseEntity.ok(filmService.updateFilm(id, film));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
        return ResponseEntity.noContent().build();
    }
}