package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StaffResponseDTO {
    private Byte staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private String username;
    private Short addressId;
    private String addressLine;
    private String city;
    private String country;
    private String phone;
    private Byte storeId;
    private LocalDateTime lastUpdate;
}
