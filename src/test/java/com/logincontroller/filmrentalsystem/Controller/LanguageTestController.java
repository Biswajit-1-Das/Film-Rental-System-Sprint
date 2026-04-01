package com.logincontroller.filmrentalsystem.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.controller.LanguageController;
import com.logincontroller.filmrentalsystem.dto.LanguageDTO;
import com.logincontroller.filmrentalsystem.service.LanguageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class LanguageTestController {

    @Mock
    LanguageService languageService;

    LanguageController languageController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        languageController = new LanguageController(languageService);
    }

    @Test
    public void deleteLanguage_returnsOkMessageAndCallsService() {
        Byte id = 1;

        ResponseEntity<String> response = languageController.deleteLanguage(id);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Language deleted successfully", response.getBody());
        verify(languageService).deleteLanguage(id);
    }

    @Test
    public void getLanguageWithFilms_delegatesToService() {
        Byte id = 1;
        LanguageDTO expected = new LanguageDTO();
        expected.setLanguageId(id);
        expected.setName("English");
        expected.setFilmTitles(List.of("Film 1"));

        when(languageService.getLanguageWithFilms(id)).thenReturn(expected);

        ResponseEntity<LanguageDTO> response = languageController.getLanguageWithFilms(id);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(languageService).getLanguageWithFilms(id);
    }
}

