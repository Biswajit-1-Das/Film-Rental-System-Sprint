package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LanguageDTO {
    private Byte languageId;
    private String name;
    private LocalDateTime lastUpdate;
    /** Populated when loading language with films */
    private List<String> filmTitles;
}
