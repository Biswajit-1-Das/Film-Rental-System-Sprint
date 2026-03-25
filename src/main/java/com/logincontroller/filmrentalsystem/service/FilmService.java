package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return filmRepository.findAll();
    }

    public Film getFilmById(Short id) {
        return filmRepository.findById(id).orElse(null);
    }

    public Film saveFilm(Film film) {
        return filmRepository.save(film);
    }

    public void deleteFilm(Short id) {
        filmRepository.deleteById(id);
    }
}