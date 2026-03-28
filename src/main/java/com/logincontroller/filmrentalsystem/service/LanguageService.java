package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.LanguageDTO;
import com.logincontroller.filmrentalsystem.model.Language;
import com.logincontroller.filmrentalsystem.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    public Language getEntityById(Integer id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Language not found with id: " + id));
    }

    public List<LanguageDTO> getAllLanguages() {
        return languageRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public LanguageDTO getLanguageById(Integer id) {
        return toDTO(getEntityById(id));
    }

    public LanguageDTO saveLanguage(Language language) {
        return toDTO(languageRepository.save(language));
    }

    public void deleteLanguage(Integer id) {
        languageRepository.deleteById(id);
    }
}