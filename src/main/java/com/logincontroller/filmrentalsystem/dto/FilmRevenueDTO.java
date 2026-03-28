package com.logincontroller.filmrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmRevenueDTO {
    private Short filmId;
    private BigDecimal revenue;
}
