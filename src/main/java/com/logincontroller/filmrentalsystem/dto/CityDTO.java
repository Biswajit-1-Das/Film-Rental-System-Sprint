package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CityDTO {
    private Short cityId;
    private String city;
    private Short countryId;
    private String countryName;
    private LocalDateTime lastUpdate;
}
