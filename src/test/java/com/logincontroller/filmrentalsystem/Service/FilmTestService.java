package com.logincontroller.filmrentalsystem.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.dto.FilmActorResponseDTO;
import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.dto.YearCountDTO;
import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import com.logincontroller.filmrentalsystem.service.FilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class FilmTestService {

    @Mock
    FilmRepository filmRepository;

    @Mock
    FilmActorRepository filmActorRepository;

    @Mock
    FilmCategoryRepository filmCategoryRepository;

    @Mock
    FilmTextRepository filmTextRepository;

    @Mock
    LanguageRepository languageRepository;

    @Mock
    CategoryRepository categoryRepository;

    @InjectMocks
    FilmService filmService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private Film film(short id, String title) {
        Film f = new Film();
        f.setFilmId(id);
        f.setTitle(title);
        f.setDescription("desc");
        f.setReleaseYear(2020);
        f.setRentalDuration((byte) 5);
        f.setRentalRate(new BigDecimal("10.50"));
        f.setLength((short) 120);
        f.setReplacementCost(new BigDecimal("20.00"));
        f.setRating("PG");
        f.setSpecialFeatures("Behind the scenes");
        f.setLastUpdate(LocalDateTime.now());
        return f;
    }

    @Test
    public void getFilmById_mapsEntityToDto() {
        Short id = 1;
        Film entity = film(id, "The Film");

        when(filmRepository.findById(id)).thenReturn(Optional.of(entity));

        FilmResponseDTO dto = filmService.getFilmById(id);

        assertEquals(id, dto.getFilmId());
        assertEquals("The Film", dto.getTitle());
    }

    @Test
    public void saveFilm_savesAndMapsToDto() {
        Film input = film((short) 1, "Old");
        Film saved = film((short) 1, "Saved");

        when(filmRepository.save(input)).thenReturn(saved);

        FilmResponseDTO dto = filmService.saveFilm(input);

        assertEquals(saved.getFilmId(), dto.getFilmId());
        assertEquals("Saved", dto.getTitle());
        verify(filmRepository).save(input);
    }

    @Test
    public void searchByTitle_returnsMappedDtos() {
        List<Film> films = List.of(
                film((short) 1, "Hello"),
                film((short) 2, "Hello 2")
        );

        when(filmRepository.findByTitleContainingIgnoreCase("hello")).thenReturn(films);

        List<FilmResponseDTO> dtos = filmService.searchByTitle("hello");

        assertEquals(2, dtos.size());
        assertEquals("Hello", dtos.get(0).getTitle());
    }

    @Test
    public void getFilmActorLinks_mapsFilmAndActors() {
        Short filmId = 1;
        Film film = film(filmId, "Film Title");
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));

        Actor actor = Actor.builder()
                .actorId((short) 10)
                .firstName("Raj")
                .lastName("K")
                .lastUpdate(LocalDateTime.now())
                .build();

        FilmActor fa = FilmActor.builder()
                .actor(actor)
                .film(film)
                .build();

        when(filmActorRepository.findByFilm_FilmId(filmId)).thenReturn(List.of(fa));

        List<FilmActorResponseDTO> links = filmService.getFilmActorLinks(filmId);

        assertEquals(1, links.size());
        FilmActorResponseDTO dto = links.get(0);
        assertEquals(filmId, dto.getFilmId());
        assertEquals((short) 10, dto.getActorId());
        assertEquals("Raj", dto.getActorFirstName());
        assertEquals("Film Title", dto.getFilmTitle());
    }

    @Test
    public void updateTitle_updatesAndMaps() {
        Short id = 1;
        Film existing = film(id, "Old");
        Film updated = film(id, "New");

        when(filmRepository.findById(id)).thenReturn(Optional.of(existing));
        when(filmRepository.save(any(Film.class))).thenAnswer(invocation -> invocation.getArgument(0));

        FilmResponseDTO dto = filmService.updateTitle(id, "New");

        assertEquals(id, dto.getFilmId());
        assertEquals("New", dto.getTitle());
        verify(filmRepository).save(any(Film.class));
    }

    @Test
    public void countFilmsByYear_mapsAggregateRows() {
        Object[] row1 = new Object[] { 2020, 2L };
        Object[] row2 = new Object[] { 2021, 1L };

        when(filmRepository.countFilmsGroupedByReleaseYear()).thenReturn(List.of(row1, row2));

        List<YearCountDTO> out = filmService.countFilmsByYear();

        assertEquals(2, out.size());
        assertEquals(2020, out.get(0).getYear());
        assertEquals(2L, out.get(0).getCount());
    }
}

