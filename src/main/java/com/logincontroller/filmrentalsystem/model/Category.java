package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId; // Maps to TINYINT

    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}