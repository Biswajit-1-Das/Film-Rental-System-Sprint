package com.logincontroller.filmrentalsystem.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.dto.ActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.model.Actor;
import com.logincontroller.filmrentalsystem.model.FilmActor;
import com.logincontroller.filmrentalsystem.repository.ActorRepository;
import com.logincontroller.filmrentalsystem.repository.FilmActorRepository;
import com.logincontroller.filmrentalsystem.repository.FilmRepository;
import com.logincontroller.filmrentalsystem.service.ActorService;
import com.logincontroller.filmrentalsystem.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

public class ActorTestService {

    @Mock
    ActorRepository actorRepository;

    @Mock
    FilmActorRepository filmActorRepository;

    @Mock
    FilmService filmService;

    @InjectMocks
    ActorService actorService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ✅ 1. getActorById
    @Test
    public void getActorById() {
        Actor actor = Actor.builder()
                .actorId((short) 1)
                .firstName("Rendi")
                .lastName("ChutChut")
                .build();

        when(actorRepository.findById((short) 1)).thenReturn(Optional.of(actor));

        ActorResponseDTO result = actorService.getActorById((short) 1);

        assertEquals("Rendi", result.getFirstName());
    }

    // ✅ 2. getAllActors
    @Test
    public void getAllActors() {

        Actor a1 = Actor.builder().actorId(Short.parseShort("1")).firstName("Raj").lastName("Karmakar").build();
        Actor a2 = Actor.builder().actorId(Short.parseShort("2")).firstName("Anishka").lastName("Das").build();

        List<Actor> actors = List.of(a1,a2);
        when(actorRepository.findAll()).thenReturn(actors);

        List<ActorResponseDTO> result = actorService.getAllActors();

        assertEquals(2, result.size());
    }

    @Test
    public void getAllActorNames() {
        Actor a1 = Actor.builder().actorId(Short.parseShort("1")).firstName("Raj").lastName("Karmakar").build();
        Actor a2 = Actor.builder().actorId(Short.parseShort("2")).firstName("Anishka").lastName("Das").build();
        List<Actor> actors = List.of(a1,a2);
        when(actorRepository.findAll()).thenReturn(actors);

        List<String> result = actorService.getAllActorNames();

        assertEquals("Raj Karmakar", result.get(0));
        assertEquals("Anishka Das", result.get(1));
    }

    @Test
    public void getFilmsForActor_delegatesToFilmService() {
        Short actorId = 1;
        FilmResponseDTO film1 = new FilmResponseDTO();
        film1.setFilmId((short) 10);
        film1.setTitle("Film A");

        List<FilmResponseDTO> films = List.of(film1);
        when(filmService.getFilmsForActor(actorId)).thenReturn(films(films));

        List<FilmResponseDTO> result = actorService.getFilmsForActor(actorId);

        assertEquals(1, result.size());
        assertEquals("Film A", result.get(0).getTitle());
        verify(filmService).getFilmsForActor(actorId);
    }

    private List<FilmResponseDTO> films(List<FilmResponseDTO> input) {
        return input;
    }

    @Test
    public void searchByFirstName_filtersExactCaseInsensitiveMatch() {
        String search = "raj";

        Actor match = Actor.builder().actorId((short) 1).firstName("Raj").lastName("K").build();
        Actor notMatch = Actor.builder().actorId((short) 2).firstName("Raja").lastName("D").build();

        when(actorRepository.findByFirstNameContainingIgnoreCase(search)).thenReturn(List.of(match, notMatch));

        List<ActorResponseDTO> result = actorService.searchByFirstName(search);
        assertEquals(1, result.size());
        assertEquals("Raj", result.get(0).getFirstName());
    }

    @Test
    public void searchByLastName_filtersExactCaseInsensitiveMatch() {
        String search = "das";

        Actor match = Actor.builder().actorId((short) 1).firstName("A").lastName("Das").build();
        Actor notMatch = Actor.builder().actorId((short) 2).firstName("B").lastName("Dashi").build();

        when(actorRepository.findByLastNameContainingIgnoreCase(search)).thenReturn(List.of(match, notMatch));

        List<ActorResponseDTO> result = actorService.searchByLastName(search);
        assertEquals(1, result.size());
        assertEquals("Das", result.get(0).getLastName());
    }

    @Test
    public void saveActor_savesAndReturnsDto() {
        Actor actor = Actor.builder().actorId((short) 1).firstName("Rendi").lastName("ChutChut").build();
        when(actorRepository.save(actor)).thenReturn(actor);

        ActorResponseDTO result = actorService.saveActor(actor);

        assertEquals("Rendi", result.getFirstName());
        assertEquals("ChutChut", result.getLastName());
        verify(actorRepository).save(actor);
    }

    @Test
    public void deleteActor_deletesById() {
        Short id = 1;
        doNothing().when(actorRepository).deleteById(id);

        actorService.deleteActor(id);

        verify(actorRepository).deleteById(id);
    }

    @Test
    public void updateLastName_updatesEntityAndReturnsDto() {
        Short id = 1;
        Actor existing = Actor.builder().actorId(id).firstName("Raj").lastName("Old").build();
        Actor saved = Actor.builder().actorId(id).firstName("Raj").lastName("New").build();

        when(actorRepository.findById(id)).thenReturn(Optional.of(existing));
        when(actorRepository.save(any(Actor.class))).thenReturn(saved);

        ActorResponseDTO result = actorService.updateLastName(id, "New");

        assertEquals("New", result.getLastName());
        verify(actorRepository).save(any(Actor.class));
    }

    @Test
    public void getTopTenActorsByFilmCount_returnsOnlyActorsWithPresentIds() {
        List<Short> ids = List.of((short) 1, (short) 2);
        when(filmActorRepository.findTopActorIdsByFilmCount()).thenReturn(ids);

        Actor a1 = Actor.builder().actorId((short) 1).firstName("Raj").lastName("A").build();
        when(actorRepository.findById((short) 1)).thenReturn(Optional.of(a1));
        when(actorRepository.findById((short) 2)).thenReturn(Optional.empty());

        List<ActorResponseDTO> result = actorService.getTopTenActorsByFilmCount();

        assertEquals(1, result.size());
        assertEquals("Raj", result.get(0).getFirstName());
    }

}