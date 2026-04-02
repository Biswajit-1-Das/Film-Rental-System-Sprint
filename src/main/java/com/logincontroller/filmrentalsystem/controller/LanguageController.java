package com.logincontroller.filmrentalsystem.controller;

import com.logincontroller.filmrentalsystem.dto.LanguageDTO;
import com.logincontroller.filmrentalsystem.model.Language;
import com.logincontroller.filmrentalsystem.service.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;

@RestController
@CrossOrigin(origins="http://10.191.27.14:9090")
@RequestMapping("/api/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public ResponseEntity<HashSet<LanguageDTO>> getAllLanguages() {
        return ResponseEntity.ok(languageService.getAllLanguages());
    }

    @GetMapping("/search")
    public ResponseEntity<LanguageDTO> getLanguageByName(@RequestParam String name) {
        return ResponseEntity.ok(languageService.getLanguageByName(name));
    }

    @GetMapping("/exists")
    public ResponseEntity<Boolean> existsByName(@RequestParam String name) {
        return ResponseEntity.ok(languageService.existsByNameIgnoreCase(name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<LanguageDTO> getLanguageById(@PathVariable Byte id) {
        return ResponseEntity.ok(languageService.getLanguageById(id));
    }

    @GetMapping("/{id}/with-films")
    public ResponseEntity<LanguageDTO> getLanguageWithFilms(@PathVariable Byte id) {
        return ResponseEntity.ok(languageService.getLanguageWithFilms(id));
    }

    @GetMapping("/{id}/film-count")
    public ResponseEntity<Long> countFilms(@PathVariable Byte id) {
        return ResponseEntity.ok(languageService.countFilmsByLanguage(id));
    }

    @PostMapping
    public ResponseEntity<LanguageDTO> createLanguage(@RequestBody Language language) {
        return ResponseEntity.ok(languageService.saveLanguage(language));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<LanguageDTO> updateLanguage(@PathVariable Byte id,
                                                      @RequestBody Language language) {
        Language existing = languageService.getEntityById(id);
        existing.setName(language.getName());
        return ResponseEntity.ok(languageService.saveLanguage(existing));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLanguage(@PathVariable Byte id) {
        languageService.deleteLanguage(id);
        return ResponseEntity.ok("Language deleted successfully");
    }
}
