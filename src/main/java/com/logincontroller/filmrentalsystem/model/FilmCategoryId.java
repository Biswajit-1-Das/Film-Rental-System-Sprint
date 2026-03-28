package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmCategoryId implements Serializable {

    @Column(name = "film_id")
    private Short filmId;

    @Column(name = "category_id")
    private Byte categoryId;
}
