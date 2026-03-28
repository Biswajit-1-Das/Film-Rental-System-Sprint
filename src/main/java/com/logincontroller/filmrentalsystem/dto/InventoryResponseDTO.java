package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryResponseDTO {
    private Integer inventoryId;
    private Short filmId;
    private String filmTitle;
    private Byte storeId;
    private LocalDateTime lastUpdate;
}
