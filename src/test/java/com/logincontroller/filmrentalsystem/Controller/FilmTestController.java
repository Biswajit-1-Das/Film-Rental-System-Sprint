package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.FilmController;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.dto.YearCountDTO;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FilmTestController {

    @Mock
    FilmService filmService;

    FilmController filmController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        filmController = new FilmController(filmService);
    }

    @Test
    public void createFilm_returnsSavedDto() {
        Film input = new Film();
        input.setFilmId((short) 1);
        input.setTitle("T1");

        FilmResponseDTO expected = new FilmResponseDTO();
        expected.setFilmId((short) 1);
        expected.setTitle("T1");

        when(filmService.saveFilm(input)).thenReturn(expected);

        ResponseEntity<FilmResponseDTO> response = filmController.createFilm(input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(filmService).saveFilm(input);
    }

    @Test
    public void byTitle_returnsList() {
        List<FilmResponseDTO> expected = List.of(new FilmResponseDTO());
        when(filmService.searchByTitle("hello")).thenReturn(expected);

        ResponseEntity<List<FilmResponseDTO>> response = filmController.byTitle("hello");

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(filmService).searchByTitle("hello");
    }

    @Test
    public void updateTitle_callsServiceAndReturnsDto() {
        Short id = 1;
        String title = "New Title";

        FilmResponseDTO expected = new FilmResponseDTO();
        expected.setFilmId(id);
        expected.setTitle(title);

        when(filmService.updateTitle(id, title)).thenReturn(expected);

        ResponseEntity<FilmResponseDTO> response = filmController.updateTitle(id, title);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(filmService).updateTitle(id, title);
    }

    @Test
    public void getFilmById_returnsDto() {
        Short id = 1;
        FilmResponseDTO expected = new FilmResponseDTO();
        expected.setFilmId(id);
        expected.setTitle("T");

        when(filmService.getFilmById(id)).thenReturn(expected);

        ResponseEntity<FilmResponseDTO> response = filmController.getFilmById(id);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(filmService).getFilmById(id);
    }

    @Test
    public void countByYear_returnsList() {
        List<YearCountDTO> expected = List.of(new YearCountDTO(2020, 2L));
        when(filmService.countFilmsByYear()).thenReturn(expected);

        ResponseEntity<List<YearCountDTO>> response = filmController.countByYear();

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(filmService).countFilmsByYear();
    }
}

