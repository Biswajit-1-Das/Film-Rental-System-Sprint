package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class CityDTO {
    private Integer cityId;
    private String city;
    private Integer countryId;
    private String countryName;
    private Timestamp lastUpdate;
}