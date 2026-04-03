package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.*;
import com.logincontroller.filmrentalsystem.exception.ResourceNotFoundException;
import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import com.logincontroller.filmrentalsystem.util.FilmRatingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class
FilmService {

    private final FilmRepository filmRepository;
    private final FilmActorRepository filmActorRepository;
    private final FilmCategoryRepository filmCategoryRepository;
    private final FilmTextRepository filmTextRepository;
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;

    public FilmResponseDTO toResponseDTO(Film film) {
        FilmResponseDTO dto = new FilmResponseDTO();
        dto.setFilmId(film.getFilmId());
        dto.setTitle(film.getTitle());
        dto.setDescription(film.getDescription());
        dto.setReleaseYear(film.getReleaseYear());
        dto.setRentalDuration(film.getRentalDuration());
        dto.setRentalRate(film.getRentalRate());
        dto.setLength(film.getLength());
        dto.setReplacementCost(film.getReplacementCost());
        dto.setRating(film.getRating());
        dto.setSpecialFeatures(film.getSpecialFeatures());
        dto.setLastUpdate(film.getLastUpdate());
        if (film.getLanguage() != null) {
            dto.setLanguageId(film.getLanguage().getLanguageId());
            dto.setLanguageName(film.getLanguage().getName());
        }
        if (film.getOriginalLanguage() != null) {
            dto.setOriginalLanguageId(film.getOriginalLanguage().getLanguageId());
            dto.setOriginalLanguageName(film.getOriginalLanguage().getName());
        }
        if (film.getFilmActors() != null) {
            dto.setActorNames(
                    film.getFilmActors().stream()
                            .map(fa -> fa.getActor().getFirstName() + " " + fa.getActor().getLastName())
                            .collect(Collectors.toList())
            );
        }
        if (film.getFilmCategories() != null) {
            dto.setCategoryNames(
                    film.getFilmCategories().stream()
                            .map(fc -> fc.getCategory().getName())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Film getEntityById(Short id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Film not found with id: " + id));
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getAllFilms() {
        return filmRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public FilmResponseDTO getFilmById(Short id) {
        return toResponseDTO(getEntityById(id));
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> searchByTitle(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByRating(String rating) {
        return filmRepository.findByRating(rating).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByLanguage(Byte languageId) {
        return filmRepository.findByLanguageLanguageId(languageId).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByLanguageName(String langName) {
        return filmRepository.findByLanguage_NameIgnoreCase(langName).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByReleaseYear(Integer releaseYear) {
        return filmRepository.findByReleaseYear(releaseYear).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsBetweenYears(Integer from, Integer to) {
        return filmRepository.findByReleaseYearBetween(from, to).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsDurationGreaterThan(Byte rd) {
        return filmRepository.findByRentalDurationGreaterThan(rd).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsDurationLessThan(Byte rd) {
        return filmRepository.findByRentalDurationLessThan(rd).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsRateGreaterThan(BigDecimal rate) {
        return filmRepository.findByRentalRateGreaterThan(rate).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsRateLessThan(BigDecimal rate) {
        return filmRepository.findByRentalRateLessThan(rate).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsLengthGreaterThan(Short length) {
        return filmRepository.findByLengthGreaterThan(length).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsLengthLessThan(Short length) {
        return filmRepository.findByLengthLessThan(length).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsRatingGreaterThan(String rating) {
        List<String> tiers = FilmRatingUtil.ratingsStrictlyGreaterThan(rating);
        if (tiers.isEmpty()) {
            return List.of();
        }
        return filmRepository.findByRatingIn(tiers).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsRatingLessThan(String rating) {
        List<String> tiers = FilmRatingUtil.ratingsStrictlyLessThan(rating);
        if (tiers.isEmpty()) {
            return List.of();
        }
        return filmRepository.findByRatingIn(tiers).stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsByCategoryName(String categoryName) {
        return filmRepository.findDistinctByFilmCategories_Category_NameIgnoreCase(categoryName)
                .stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<YearCountDTO> countFilmsByYear() {
        return filmRepository.countFilmsGroupedByReleaseYear().stream()
                .map(row -> new YearCountDTO((Integer) row[0], ((Number) row[1]).longValue()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getAllFilmTitlesOnly() {
        return filmRepository.findAll().stream()
                .map(f -> {
                    FilmResponseDTO d = new FilmResponseDTO();
                    d.setFilmId(f.getFilmId());
                    d.setTitle(f.getTitle());
                    return d;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ActorResponseDTO> getActorsForFilm(Short filmId) {
        return filmActorRepository.findByFilm_FilmId(filmId).stream()
                .map(fa -> {
                    Actor a = fa.getActor();
                    ActorResponseDTO d = new ActorResponseDTO();
                    d.setActorId(a.getActorId());
                    d.setFirstName(a.getFirstName());
                    d.setLastName(a.getLastName());
                    d.setLastUpdate(a.getLastUpdate());
                    return d;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmActorResponseDTO> getFilmActorLinks(Short filmId) {
        Film film = getEntityById(filmId);
        return filmActorRepository.findByFilm_FilmId(filmId).stream()
                .map(fa -> new FilmActorResponseDTO(
                        film.getFilmId(),
                        fa.getActor().getActorId(),
                        fa.getActor().getFirstName(),
                        fa.getActor().getLastName(),
                        film.getTitle()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmActorResponseDTO> getActorFilmLinks(Short actorId) {
        return filmActorRepository.findByActor_ActorId(actorId).stream()
                .map(fa -> new FilmActorResponseDTO(
                        fa.getFilm().getFilmId(),
                        fa.getActor().getActorId(),
                        fa.getActor().getFirstName(),
                        fa.getActor().getLastName(),
                        fa.getFilm().getTitle()
                ))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<FilmResponseDTO> getFilmsForActor(Short actorId) {
        List<FilmResponseDTO> res = new ArrayList<>();
        List<FilmActor> ar = filmActorRepository.findByActor_ActorId(actorId);
        for(FilmActor f : ar)
        {
            if(f.getId().equals(actorId))
            {
                res.add(toResponseDTO(f.getFilm()));
            }
        }
        return res;
    }

    @Transactional(readOnly = true)
    public Film_TextResponseDTO getFilmText(Short filmId) {
        Film_Text ft = filmTextRepository.findById(filmId)
                .orElseThrow(() -> new ResourceNotFoundException("film_text not found for film id: " + filmId));
        Film_TextResponseDTO d = new Film_TextResponseDTO();
        d.setFilmId(ft.getFilmId());
        d.setTitle(ft.getTitle());
        d.setDescription(ft.getDescription());
        return d;
    }

    @Transactional
    public FilmResponseDTO saveFilm(Film film) {
        return toResponseDTO(filmRepository.save(film));
    }

    public void deleteFilm(Short id) {
        filmRepository.deleteById(id);
    }

    @Transactional
    public FilmResponseDTO updateTitle(Short id, String title) {
        Film f = getEntityById(id);
        f.setTitle(title);
        return toResponseDTO(filmRepository.save(f));
    }

    @Transactional
    public FilmResponseDTO updateReleaseYear(Short id, Integer year) {
        Film f = getEntityById(id);
        f.setReleaseYear(year);
        return toResponseDTO(filmRepository.save(f));
    }

    @Transactional
    public FilmResponseDTO updateRentalDuration(Short id, Byte rentalDuration) {
        Film f = getEntityById(id);
        f.setRentalDuration(rentalDuration);
        return toResponseDTO(filmRepository.save(f));
    }

    @Transactional
    public FilmResponseDTO updateRentalRate(Short id, BigDecimal rate) {
        Film f = getEntityById(id);
        f.setRentalRate(rate);
        return toResponseDTO(filmRepository.save(f));
    }

    @Transactional
    public FilmResponseDTO updateRating(Short id, String rating) {
        Film f = getEntityById(id);
        f.setRating(rating);
        return toResponseDTO(filmRepository.save(f));
    }

    @Transactional
    public FilmResponseDTO updateLanguage(Short id, Byte languageId) {
        Film f = getEntityById(id);
        Language lang = languageRepository.findById(languageId)
                .orElseThrow(() -> new ResourceNotFoundException("Language not found: " + languageId));
        f.setLanguage(lang);
        return toResponseDTO(filmRepository.save(f));
    }

    @Transactional
    public FilmResponseDTO updateCategory(Short filmId, Byte categoryId) {
        Film film = getEntityById(filmId);
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found: " + categoryId));
        filmCategoryRepository.deleteByFilm_FilmId(filmId);
        FilmCategory fc = new FilmCategory();
        FilmCategoryId pk = new FilmCategoryId(film.getFilmId(), category.getCategoryId());
        fc.setId(pk);
        fc.setFilm(film);
        fc.setCategory(category);
        fc.setLastUpdate(LocalDateTime.now());
        filmCategoryRepository.save(fc);
        return toResponseDTO(getEntityById(filmId));
    }
}
