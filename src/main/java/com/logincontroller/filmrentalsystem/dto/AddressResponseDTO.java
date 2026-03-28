package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddressResponseDTO {
    private Short addressId;
    private String address;
    private String address2;
    private String district;
    private Short cityId;
    private String cityName;
    private Short countryId;
    private String countryName;
    private String postalCode;
    private String phone;
    private String location;
    private LocalDateTime lastUpdate;
}
