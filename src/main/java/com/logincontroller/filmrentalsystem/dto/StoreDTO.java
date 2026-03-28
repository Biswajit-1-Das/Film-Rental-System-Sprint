package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class StoreDTO {
    private Integer storeId;
    private Integer managerStaffId;
    private String managerFirstName;
    private String managerLastName;
    private Integer addressId;
    private String addressLine;
    private String city;
    private String country;
    private LocalDateTime lastUpdate;
}