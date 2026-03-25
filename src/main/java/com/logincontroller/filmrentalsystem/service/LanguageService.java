package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Language;
import com.logincontroller.filmrentalsystem.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LanguageService {

    private final LanguageRepository languageRepository;

    /**
     * Get all languages
     */
    public List<Language> getAllLanguages() {
        return languageRepository.findAll();
    }

    /**
     * Get language by ID
     */
    public Language getLanguageById(Integer id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found with id: " + id));
    }

    /**
     * Get language by name
     */
    public Language getLanguageByName(String name) {
        return languageRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Language not found: " + name));
    }

    /**
     * Get language with all films
     */
    public Language getLanguageWithFilms(Integer id) {
        return languageRepository.findByIdWithFilms(id)
                .orElseThrow(() -> new RuntimeException("Language not found with id: " + id));
    }

    /**
     * Count films using this language
     */
    public Long countFilmsByLanguage(Integer languageId) {
        return languageRepository.countFilmsByLanguage(languageId);
    }

    /**
     * Check if language exists
     */
    public boolean languageExists(String name) {
        return languageRepository.existsByNameIgnoreCase(name);
    }

    /**
     * Create new language
     */
    @Transactional
    public Language createLanguage(String name) {
        if (languageRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Language already exists: " + name);
        }

        Language language = Language.builder()
                .name(name)
                .build();

        return languageRepository.save(language);
    }

    /**
     * Update language
     */
    @Transactional
    public Language updateLanguage(Integer id, String name) {
        Language language = getLanguageById(id);

        // Check if new name conflicts with existing language
        if (!language.getName().equalsIgnoreCase(name) &&
                languageRepository.existsByNameIgnoreCase(name)) {
            throw new RuntimeException("Language name already exists: " + name);
        }

        language.setName(name);
        return languageRepository.save(language);
    }

    /**
     * Delete language (only if no films are using it)
     */
    @Transactional
    public void deleteLanguage(Integer id) {
        Language language = getLanguageById(id);

        Long filmCount = countFilmsByLanguage(id);
        if (filmCount > 0) {
            throw new RuntimeException(
                    "Cannot delete language. " + filmCount + " films are using it.");
        }

        languageRepository.delete(language);
    }
}