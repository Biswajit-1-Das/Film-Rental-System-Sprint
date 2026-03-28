package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;
import java.sql.Timestamp;
import java.util.List;

@Data
public class ActorDTO {
    private Short actorId;
    private String firstName;
    private String lastName;
    private Timestamp lastUpdate;
    private List<String> filmTitles;
}