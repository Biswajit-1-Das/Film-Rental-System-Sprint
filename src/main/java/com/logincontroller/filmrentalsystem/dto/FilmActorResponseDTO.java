package com.logincontroller.filmrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmActorResponseDTO {
    private Short filmId;
    private Short actorId;
    private String actorFirstName;
    private String actorLastName;
    private String filmTitle;
}
