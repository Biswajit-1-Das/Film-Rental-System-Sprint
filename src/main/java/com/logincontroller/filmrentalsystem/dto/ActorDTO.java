package com.logincontroller.filmrentalsystem.dto;

import com.logincontroller.filmrentalsystem.model.Actor;
import lombok.Data;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class ActorDTO {
    private Short actorId;
    private String firstName;
    private String lastName;
    private LocalDateTime lastUpdate;

    // This constructor does all the heavy lifting for you!
    public ActorDTO(Actor actor) {
        this.actorId = actor.getActorId();
        this.firstName = actor.getFirstName();
        this.lastName = actor.getLastName();
        this.lastUpdate = actor.getLastUpdate();
    }
}