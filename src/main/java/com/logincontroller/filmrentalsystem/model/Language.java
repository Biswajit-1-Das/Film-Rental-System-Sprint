package com.logincontroller.filmrentalsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore; // ADD THIS IMPORT
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "language")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Language {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "language_id")
    private Byte languageId;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "last_update", nullable = false,
            insertable = false, updatable = false)
    private LocalDateTime lastUpdate;

    // Inverse side — films using this as primary language
    @OneToMany(mappedBy = "language", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // 🔥 CRITICAL FIX: Stops the infinite loop!
    private Set<Film> films = new HashSet<>();

    // Inverse side — films using this as original language
    @OneToMany(mappedBy = "originalLanguage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore // 🔥 CRITICAL FIX: Stops the infinite loop!
    private Set<Film> originalLanguageFilms = new HashSet<>();

    public void addFilm(Film film) {
        films.add(film);
        film.setLanguage(this);
    }

    public void removeFilm(Film film) {
        films.remove(film);
        film.setLanguage(null);
    }

    public void addOriginalLanguageFilm(Film film) {
        originalLanguageFilms.add(film);
        film.setOriginalLanguage(this);
    }

    public void removeOriginalLanguageFilm(Film film) {
        originalLanguageFilms.remove(film);
        film.setOriginalLanguage(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Language)) return false;
        Language language = (Language) o;
        return languageId != null && languageId.equals(language.languageId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Language{" +
                "languageId=" + languageId +
                ", name='" + name + '\'' +
                '}';
    }
}