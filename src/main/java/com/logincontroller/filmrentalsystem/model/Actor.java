package com.logincontroller.filmrentalsystem.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "actor")
@NoArgsConstructor //creates empty constructor
@AllArgsConstructor //creates constructor with all fields
@Data
@Builder //Helps create object like: Actor actor = Actor.builder()
    //                                       .firstName("Tom")
   //                                        .lastName("Hanks")
   //                                        .build();
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id")
    private Short actorId;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @CreationTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    @OneToMany(mappedBy = "actor")
    @JsonIgnore
    private List<FilmActor> filmActors = new java.util.ArrayList<>();
}
