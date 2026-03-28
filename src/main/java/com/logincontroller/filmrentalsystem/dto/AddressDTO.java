package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class AddressDTO {
    private Integer addressId;
    private String address;
    private String address2;
    private String district;
    private Integer cityId;
    private String cityName;
    private Integer countryId;
    private String countryName;
    private String postalCode;
    private String phone;
    private String location;
    private LocalDateTime lastUpdate;
}