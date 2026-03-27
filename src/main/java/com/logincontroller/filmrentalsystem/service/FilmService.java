package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.repository.FilmRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true) // Fast reads by default
public class FilmService {

    private final FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Short id) {
        return filmRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Film not found with id: " + id));
    }

    @Transactional // Override for write operation
    public Film createFilm(Film film) {
        film.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        return filmRepository.save(film);
    }

    @Transactional // Safe update operation
    public Film updateFilm(Short id, Film filmDetails) {
        Film existing = getFilmById(id);
        
        // Safely update fields without risking accidental nullification
        existing.setTitle(filmDetails.getTitle());
        existing.setDescription(filmDetails.getDescription());
        existing.setReleaseYear(filmDetails.getReleaseYear());
        existing.setRentalDuration(filmDetails.getRentalDuration());
        existing.setRentalRate(filmDetails.getRentalRate());
        existing.setLength(filmDetails.getLength());
        existing.setReplacementCost(filmDetails.getReplacementCost());
        existing.setRating(filmDetails.getRating());
        existing.setSpecialFeatures(filmDetails.getSpecialFeatures());
        
        existing.setLanguage(filmDetails.getLanguage());
        existing.setOriginalLanguage(filmDetails.getOriginalLanguage());
        
        existing.setLastUpdate(new Timestamp(System.currentTimeMillis()));
        return filmRepository.save(existing);
    }

    @Transactional
    public void deleteFilm(Short id) {
        Film film = getFilmById(id); // Ensure it exists before deleting
        filmRepository.delete(film);
    }

    // --- Custom Filters ---
    public List<Film> searchFilmsByTitle(String title) {
        return filmRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Film> getFilmsByLanguage(Integer languageId) {
        return filmRepository.findByLanguage_LanguageId(languageId);
    }
}