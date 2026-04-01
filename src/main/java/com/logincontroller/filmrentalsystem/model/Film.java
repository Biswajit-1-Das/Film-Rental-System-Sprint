package com.logincontroller.filmrentalsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "film")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private Integer releaseYear;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating")
    private String rating;

    @Column(name = "special_features")
    private String specialFeatures;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id", nullable = false)
    private Language language;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "original_language_id", nullable = true)
    private Language originalLanguage;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FilmActor> filmActors = new ArrayList<>();


    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Inventory> inventories = new ArrayList<>();

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<FilmCategory> filmCategories = new ArrayList<>();
}