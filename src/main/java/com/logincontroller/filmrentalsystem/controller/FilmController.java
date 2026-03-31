package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.*;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.service.FilmService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/film")
@RequiredArgsConstructor
public class FilmController {

    private final FilmService filmService;

    @PostMapping("/post")
    public ResponseEntity<FilmResponseDTO> createFilm(@RequestBody Film film) {
        return ResponseEntity.ok(filmService.saveFilm(film));
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<List<FilmResponseDTO>> byTitle(@PathVariable String title) {
        return ResponseEntity.ok(filmService.searchByTitle(title));
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<FilmResponseDTO>> byYear(@PathVariable Integer year) {
        return ResponseEntity.ok(filmService.getFilmsByReleaseYear(year));
    }

    @GetMapping("/duration/gt/{rd}")
    public ResponseEntity<List<FilmResponseDTO>> durationGt(@PathVariable Byte rd) {
        return ResponseEntity.ok(filmService.getFilmsDurationGreaterThan(rd));
    }

    @GetMapping("/rate/gt/{rate}")
    public ResponseEntity<List<FilmResponseDTO>> rateGt(@PathVariable BigDecimal rate) {
        return ResponseEntity.ok(filmService.getFilmsRateGreaterThan(rate));
    }

    @GetMapping("/length/gt/{length}")
    public ResponseEntity<List<FilmResponseDTO>> lengthGt(@PathVariable Short length) {
        return ResponseEntity.ok(filmService.getFilmsLengthGreaterThan(length));
    }

    @GetMapping("/duration/lt/{rd}")
    public ResponseEntity<List<FilmResponseDTO>> durationLt(@PathVariable Byte rd) {
        return ResponseEntity.ok(filmService.getFilmsDurationLessThan(rd));
    }

    @GetMapping("/rate/lt/{rate}")
    public ResponseEntity<List<FilmResponseDTO>> rateLt(@PathVariable BigDecimal rate) {
        return ResponseEntity.ok(filmService.getFilmsRateLessThan(rate));
    }

    @GetMapping("/length/lt/{length}")
    public ResponseEntity<List<FilmResponseDTO>> lengthLt(@PathVariable Short length) {
        return ResponseEntity.ok(filmService.getFilmsLengthLessThan(length));
    }

    @GetMapping("/betweenyear/{from}/{to}")
    public ResponseEntity<List<FilmResponseDTO>> betweenYear(
            @PathVariable Integer from,
            @PathVariable Integer to) {
        return ResponseEntity.ok(filmService.getFilmsBetweenYears(from, to));
    }

    @GetMapping("/rating/lt/{rating}")
    public ResponseEntity<List<FilmResponseDTO>> ratingLt(@PathVariable String rating) {
        return ResponseEntity.ok(filmService.getFilmsRatingLessThan(rating));
    }

    @GetMapping("/rating/gt/{rating}")
    public ResponseEntity<List<FilmResponseDTO>> ratingGt(@PathVariable String rating) {
        return ResponseEntity.ok(filmService.getFilmsRatingGreaterThan(rating));
    }

    @GetMapping("/language/{lang}")
    public ResponseEntity<List<FilmResponseDTO>> byLanguageName(@PathVariable String lang) {
        return ResponseEntity.ok(filmService.getFilmsByLanguageName(lang));
    }

    @GetMapping("/countbyyear")
    public ResponseEntity<List<YearCountDTO>> countByYear() {
        return ResponseEntity.ok(filmService.countFilmsByYear());
    }

    @GetMapping("/{id}/actors")
    public ResponseEntity<List<ActorResponseDTO>> actorsForFilm(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getActorsForFilm(id));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<FilmResponseDTO>> byCategory(@PathVariable String category) {
        return ResponseEntity.ok(filmService.getFilmsByCategoryName(category));
    }

    @GetMapping("/{id}/actor")
    public ResponseEntity<List<FilmActorResponseDTO>> filmActorLinks(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getFilmActorLinks(id));
    }

    @PutMapping("/update/title/{id}")
    public ResponseEntity<FilmResponseDTO> updateTitle(@PathVariable Short id, @RequestParam String title) {
        return ResponseEntity.ok(filmService.updateTitle(id, title));
    }

    @PutMapping("/update/releaseyear/{id}")
    public ResponseEntity<FilmResponseDTO> updateReleaseYear(@PathVariable Short id, @RequestParam Integer year) {
        return ResponseEntity.ok(filmService.updateReleaseYear(id, year));
    }

    @PutMapping("/update/rentalduration/{id}")
    public ResponseEntity<FilmResponseDTO> updateRentalDuration(@PathVariable Short id, @RequestParam Byte rentalDuration) {
        return ResponseEntity.ok(filmService.updateRentalDuration(id, rentalDuration));
    }

    @PutMapping("/update/rentalrate/{id}")
    public ResponseEntity<FilmResponseDTO> updateRentalRate(@PathVariable Short id, @RequestParam BigDecimal rentalRate) {
        return ResponseEntity.ok(filmService.updateRentalRate(id, rentalRate));
    }

    @PutMapping("/update/rating/{id}")
    public ResponseEntity<FilmResponseDTO> updateRating(@PathVariable Short id, @RequestParam String rating) {
        return ResponseEntity.ok(filmService.updateRating(id, rating));
    }

    @PutMapping("/update/language/{id}")
    public ResponseEntity<FilmResponseDTO> updateLanguage(@PathVariable Short id, @RequestParam Byte languageId) {
        return ResponseEntity.ok(filmService.updateLanguage(id, languageId));
    }

    @PutMapping("/update/category/{id}")
    public ResponseEntity<FilmResponseDTO> updateCategory(@PathVariable Short id, @RequestParam Byte categoryId) {
        return ResponseEntity.ok(filmService.updateCategory(id, categoryId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilmResponseDTO> getFilmById(@PathVariable Short id) {
        return ResponseEntity.ok(filmService.getFilmById(id));
    }

    @GetMapping
    public ResponseEntity<List<FilmResponseDTO>> getAllFilms() {
        return ResponseEntity.ok(filmService.getAllFilms());
    }
}
