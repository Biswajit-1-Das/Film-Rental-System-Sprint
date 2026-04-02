package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LanguageDTO {
    private Byte languageId;
    private String name;
    private LocalDateTime lastUpdate;
    private List<String> filmTitles;
}
