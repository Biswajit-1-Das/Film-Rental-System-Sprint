package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RentalResponseDTO {
    private Integer rentalId;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private Integer inventoryId;
    private Short filmId;
    private String filmTitle;
    private Short customerId;
    private String customerName;
    private Byte staffId;
    private String staffName;
    private LocalDateTime lastUpdate;
}
