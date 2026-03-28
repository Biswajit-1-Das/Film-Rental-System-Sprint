package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CategoryDTO {
    private Byte categoryId;
    private String name;
    private LocalDateTime lastUpdate;
    private List<String> filmTitles;
}
