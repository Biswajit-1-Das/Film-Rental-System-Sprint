package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.LanguageDTO;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.model.Language;
import com.logincontroller.filmrentalsystem.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LanguageService {

    private final LanguageRepository languageRepository;

    private LanguageDTO toDTO(Language language) {
        LanguageDTO dto = new LanguageDTO();
        dto.setLanguageId(language.getLanguageId());
        dto.setName(language.getName());
        dto.setLastUpdate(language.getLastUpdate());
        return dto;
    }

    public Language getEntityById(Byte id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found with id: " + id));
    }

    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LanguageDTO getLanguageById(Byte id) {
        return toDTO(getEntityById(id));
    }

    public LanguageDTO getLanguageByName(String name) {
        Language language = languageRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new RuntimeException("Language not found: " + name));
        return toDTO(language);
    }

    public boolean existsByNameIgnoreCase(String name) {
        return languageRepository.existsByNameIgnoreCase(name);
    }

    @Transactional(readOnly = true)
    public LanguageDTO getLanguageWithFilms(Byte id) {
        Language language = languageRepository.findByIdWithFilms(id)
                .orElseThrow(() -> new RuntimeException("Language not found with id: " + id));
        LanguageDTO dto = toDTO(language);
        if (language.getFilms() != null) {
            dto.setFilmTitles(
                    language.getFilms().stream()
                            .map(Film::getTitle)
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public long countFilmsByLanguage(Byte languageId) {
        Long c = languageRepository.countFilmsByLanguage(languageId);
        return c != null ? c : 0L;
    }

    public LanguageDTO saveLanguage(Language language) {
        return toDTO(languageRepository.save(language));
    }

    public void deleteLanguage(Byte id) {
        languageRepository.deleteById(id);
    }
}
