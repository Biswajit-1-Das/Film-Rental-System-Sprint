package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StoreDTO {
    private Byte storeId;
    private Byte managerStaffId;
    private String managerFirstName;
    private String managerLastName;
    private Short addressId;
    private String addressLine;
    private String city;
    private String country;
    private LocalDateTime lastUpdate;
}
