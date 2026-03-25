package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.model.Language;
import com.logincontroller.filmrentalsystem.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    /**
     * GET /api/languages
     * Get all languages
     */
    @GetMapping
    public ResponseEntity<List<Language>> getAllLanguages() {
        List<Language> languages = languageService.getAllLanguages();
        return ResponseEntity.ok(languages);
    }

    /**
     * GET /api/languages/{id}
     * Get language by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Language> getLanguageById(@PathVariable Integer id) {
        Language language = languageService.getLanguageById(id);
        return ResponseEntity.ok(language);
    }

    /**
     * GET /api/languages/{id}/films
     * Get language with all films
     */
    @GetMapping("/{id}/films")
    public ResponseEntity<Language> getLanguageWithFilms(@PathVariable Integer id) {
        Language language = languageService.getLanguageWithFilms(id);
        return ResponseEntity.ok(language);
    }

    /**
     * GET /api/languages/{id}/count
     * Get count of films using this language
     */
    @GetMapping("/{id}/count")
    public ResponseEntity<Long> getFilmCount(@PathVariable Integer id) {
        Long count = languageService.countFilmsByLanguage(id);
        return ResponseEntity.ok(count);
    }

    /**
     * POST /api/languages
     * Create new language
     */
    @PostMapping
    public ResponseEntity<Language> createLanguage(@RequestBody LanguageRequest request) {
        Language language = languageService.createLanguage(request.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(language);
    }

    /**
     * PUT /api/languages/{id}
     * Update language
     */
    @PutMapping("/{id}")
    public ResponseEntity<Language> updateLanguage(
            @PathVariable Integer id,
            @RequestBody LanguageRequest request) {
        Language language = languageService.updateLanguage(id, request.getName());
        return ResponseEntity.ok(language);
    }

    /**
     * DELETE /api/languages/{id}
     * Delete language
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLanguage(@PathVariable Integer id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Request DTO for language creation/update
     */
    public static class LanguageRequest {
        private String name;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
