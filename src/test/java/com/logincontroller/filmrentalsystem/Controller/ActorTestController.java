package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.ActorController;
import com.logincontroller.filmrentalsystem.dto.ActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.service.ActorService;
import com.logincontroller.filmrentalsystem.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import java.util.List;

public class ActorTestController {

    @Mock
    ActorService actorService;

    @Mock
    FilmService filmService;

    ActorController actorController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        actorController = new ActorController(actorService, filmService);
    }

    @Test
    public void createActor_returnsOkBody() {
        Actor actor = Actor.builder().actorId((short) 1).firstName("A").lastName("B").build();
        ActorResponseDTO dto = new ActorResponseDTO();
        dto.setActorId((short) 1);
        dto.setFirstName("A");
        dto.setLastName("B");

        when(actorService.saveActor(actor)).thenReturn(dto);

        ResponseEntity<ActorResponseDTO> response = actorController.createActor(actor);

        assertEquals(200, response.getStatusCode().value());
        assertSame(dto, response.getBody());
        verify(actorService).saveActor(actor);
    }

    @Test
    public void byLastName_returnsList() {
        String ln = "Das";
        List<ActorResponseDTO> expected = List.of(new ActorResponseDTO());
        when(actorService.searchByLastName(ln)).thenReturn(expected);

        ResponseEntity<List<ActorResponseDTO>> response = actorController.byLastName(ln);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(actorService).searchByLastName(ln);
    }

    @Test
    public void byFirstName_returnsList() {
        String fn = "Raj";
        List<ActorResponseDTO> expected = List.of(new ActorResponseDTO());
        when(actorService.searchByFirstName(fn)).thenReturn(expected);

        ResponseEntity<List<ActorResponseDTO>> response = actorController.byFirstName(fn);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(actorService).searchByFirstName(fn);
    }

    @Test
    public void updateLastName_updatesAndReturnsDto() {
        Short id = 1;
        String lastName = "New";
        ActorResponseDTO expected = new ActorResponseDTO();
        when(actorService.updateLastName(id, lastName)).thenReturn(expected);

        ResponseEntity<ActorResponseDTO> response = actorController.updateLastName(id, lastName);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(actorService).updateLastName(id, lastName);
    }

    @Test
    public void filmsForActor_returnsFilms() {
        Short actorId = 1;
        List<FilmResponseDTO> expected = List.of(new FilmResponseDTO());
        when(actorService.getFilmsForActor(actorId)).thenReturn(expected);

        ResponseEntity<List<FilmResponseDTO>> response = actorController.filmsForActor(actorId);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(actorService).getFilmsForActor(actorId);
    }

    @Test
    public void actorFilmLinks_returnsFilmLinks() {
        Short actorId = 1;
        List<FilmActorResponseDTO> expected = List.of(new FilmActorResponseDTO());
        when(filmService.getActorFilmLinks(actorId)).thenReturn(expected);

        ResponseEntity<List<FilmActorResponseDTO>> response = actorController.actorFilmLinks(actorId);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(filmService).getActorFilmLinks(actorId);
    }

    @Test
    public void topTenByFilmCount_returnsList() {
        List<ActorResponseDTO> expected = List.of(new ActorResponseDTO());
        when(actorService.getTopTenActorsByFilmCount()).thenReturn(expected);

        ResponseEntity<List<ActorResponseDTO>> response = actorController.topTenByFilmCount();

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(actorService).getTopTenActorsByFilmCount();
    }

    @Test
    public void getActorById_returnsDto() {
        Short id = 1;
        ActorResponseDTO expected = new ActorResponseDTO();
        when(actorService.getActorById(id)).thenReturn(expected);

        ResponseEntity<ActorResponseDTO> response = actorController.getActorById(id);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(actorService).getActorById(id);
    }
}

