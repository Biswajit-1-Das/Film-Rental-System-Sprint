package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "country")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Integer countryId;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @Column(name = "last_update", nullable = false,
            insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}