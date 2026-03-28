package com.logincontroller.filmrentalsystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YearCountDTO {
    private Integer year;
    private Long count;
}
