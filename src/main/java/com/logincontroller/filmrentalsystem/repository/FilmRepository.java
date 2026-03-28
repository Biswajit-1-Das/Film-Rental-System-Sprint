package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {
    
    // Custom filter for searching by title
    List<Film> findByTitleContainingIgnoreCase(String title);
    
    // Custom filter for finding films by language
    List<Film> findByLanguage_LanguageId(Integer languageId);
}