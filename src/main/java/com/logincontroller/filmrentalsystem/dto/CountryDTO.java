package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CountryDTO {
    private Short countryId;
    private String country;
    private LocalDateTime lastUpdate;
}
