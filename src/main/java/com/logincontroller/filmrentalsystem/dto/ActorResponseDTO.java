package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ActorResponseDTO {
    private Short actorId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastUpdate;
    private List<String> filmTitles;
}
