package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

@Data
public class Film_TextResponseDTO {
    private Short filmId;
    private String title;
    private String description;
}
