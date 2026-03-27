package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film_text")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Film_Text {

    @Id
    @Column(name = "film_id")
    private Integer filmId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // One-to-one relationship with Film
    // film_text shares the same primary key as film (film_id)
    // so no separate join column is needed
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;
}