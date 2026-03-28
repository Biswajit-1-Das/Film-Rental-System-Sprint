package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StaffDTO {
    private Integer staffId;
    private String firstName;
    private String lastName;
    private String email;
    private Boolean active;
    private String username;
    // password intentionally excluded — never expose in API response
    private Integer addressId;
    private String addressLine;
    private String city;
    private String country;
    private Integer storeId;
    private LocalDateTime lastUpdate;
}