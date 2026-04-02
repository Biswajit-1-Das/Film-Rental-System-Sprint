package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "film_text")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Film_Text {

    @Id
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "film_id")
    private Film film;
}