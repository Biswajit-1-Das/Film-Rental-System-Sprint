package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InventoryDTO {
    private Integer inventoryId;
    private Short filmId;
    private Byte storeId;
    private LocalDateTime lastUpdate;
}
