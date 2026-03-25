package com.logincontroller.filmrentalsystem.controller;


import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.service.FilmService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final FilmService filmService;

    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> getAllFilms() {
        return filmService.getAllFilms();
    }

    @GetMapping("/{id}")
    public Film getFilmById(@PathVariable Short id) {
        return filmService.getFilmById(id);
    }

    @PostMapping
    public Film createFilm(@RequestBody Film film) {
        return filmService.saveFilm(film);
    }

    @PutMapping("/{id}")
    public Film updateFilm(@PathVariable Short id, @RequestBody Film film) {
        film.setFilmId(id);
        return filmService.saveFilm(film);
    }

    @DeleteMapping("/{id}")
    public String deleteFilm(@PathVariable Short id) {
        filmService.deleteFilm(id);
        return "Film deleted successfully!";
    }
}