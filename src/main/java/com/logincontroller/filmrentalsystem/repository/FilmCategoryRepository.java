package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.FilmCategory;
import com.logincontroller.filmrentalsystem.model.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {

    List<FilmCategory> findByFilm_FilmId(Short filmId);

    List<FilmCategory> findByCategory_CategoryId(Byte categoryId);

    @Modifying
    @Query("DELETE FROM FilmCategory fc WHERE fc.film.filmId = :filmId")
    void deleteByFilm_FilmId(@Param("filmId") Short filmId);
}
