package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.LanguageDTO;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.model.Language;
import com.logincontroller.filmrentalsystem.repository.LanguageRepository;
import com.logincontroller.filmrentalsystem.service.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class LanguageTestService {

    @Mock
    LanguageRepository languageRepository;

    @InjectMocks
    LanguageService languageService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getLanguageWithFilms_populatesFilmTitles() {
        Byte id = 1;

        Film f = new Film();
        f.setFilmId((short) 1);
        f.setTitle("Film 1");

        Language lang = new Language();
        lang.setLanguageId(id);
        lang.setName("English");
        lang.setLastUpdate(LocalDateTime.now());
        lang.setFilms(new HashSet<>(List.of(f)));

        when(languageRepository.findByIdWithFilms(id)).thenReturn(Optional.of(lang));

        LanguageDTO dto = languageService.getLanguageWithFilms(id);

        assertEquals(id, dto.getLanguageId());
        assertEquals("English", dto.getName());
        assertNotNull(dto.getFilmTitles());
        assertEquals(1, dto.getFilmTitles().size());
        assertEquals("Film 1", dto.getFilmTitles().get(0));
        verify(languageRepository).findByIdWithFilms(id);
    }

    @Test
    public void countFilmsByLanguage_returnsZeroWhenNull() {
        Byte langId = 2;
        when(languageRepository.countFilmsByLanguage(langId)).thenReturn(null);

        long count = languageService.countFilmsByLanguage(langId);

        assertEquals(0L, count);
        verify(languageRepository).countFilmsByLanguage(langId);
    }
}

