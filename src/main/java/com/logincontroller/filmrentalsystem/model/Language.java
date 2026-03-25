package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;




    @Entity
    @Table(name = "language")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public class Language {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "language_id")
        private Integer languageId;

        @Column(name = "name", nullable = false, length = 20)
        private String name;

        @Column(name = "last_update", nullable = false,
                insertable = false, updatable = false)
        private LocalDateTime lastUpdate;

        // Relationship: One Language can be used by many Films
        // This is the inverse side - Film entity owns the relationship
        @OneToMany(mappedBy = "language", fetch = FetchType.LAZY)
        private Set<Film> films = new HashSet<>();

        // Relationship: One Language can be the original language for many Films
        @OneToMany(mappedBy = "originalLanguage", fetch = FetchType.LAZY)
        private Set<Film> originalLanguageFilms = new HashSet<>();

        // Helper method to add film
        public void addFilm(Film film) {
            films.add(film);
            film.setLanguage(this);
        }

        // Helper method to remove film
        public void removeFilm(Film film) {
            films.remove(film);
            film.setLanguage(null);
        }

        @Override
        public String toString() {
            return "Language{" +
                    "languageId=" + languageId +
                    ", name='" + name + '\'' +
                    '}';
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
    }

