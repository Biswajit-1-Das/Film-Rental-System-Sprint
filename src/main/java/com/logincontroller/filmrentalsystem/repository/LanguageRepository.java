package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Byte> {

    Optional<Language> findByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT DISTINCT l FROM Language l LEFT JOIN FETCH l.films WHERE l.languageId = :id")
    Optional<Language> findByIdWithFilms(@Param("id") Byte id);

    @Query("SELECT COUNT(f) FROM Film f WHERE f.language.languageId = :languageId")
    Long countFilmsByLanguage(@Param("languageId") Byte languageId);
}
