package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class FilmResponseDTO {
    private Short filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Byte languageId;
    private String languageName;
    private Byte originalLanguageId;
    private String originalLanguageName;
    private Byte rentalDuration;
    private BigDecimal rentalRate;
    private Short length;
    private BigDecimal replacementCost;
    private String rating;
    private String specialFeatures;
    private LocalDateTime lastUpdate;
    private List<String> actorNames;
    private List<String> categoryNames;
}
