package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CustomerResponseDTO {
    private Short customerId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private LocalDateTime createDate;
    private Byte storeId;
    private Short addressId;
    private String addressLine;
    private String city;
    private String country;
    private String postalCode;
    private String phone;
    private LocalDateTime lastUpdate;
}
