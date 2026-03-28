package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Short> {

    List<Film> findByTitleContainingIgnoreCase(String title);

    List<Film> findByRating(String rating);

    List<Film> findByLanguageLanguageId(Byte languageId);

    List<Film> findByReleaseYear(Integer releaseYear);

    List<Film> findByReleaseYearBetween(Integer fromYear, Integer toYear);

    List<Film> findByRentalDurationGreaterThan(Byte rentalDuration);

    List<Film> findByRentalDurationLessThan(Byte rentalDuration);

    List<Film> findByRentalRateGreaterThan(BigDecimal rate);

    List<Film> findByRentalRateLessThan(BigDecimal rate);

    List<Film> findByLengthGreaterThan(Short length);

    List<Film> findByLengthLessThan(Short length);

    List<Film> findByLanguage_NameIgnoreCase(String languageName);

    List<Film> findDistinctByFilmCategories_Category_NameIgnoreCase(String categoryName);

    List<Film> findByRatingIn(Collection<String> ratings);

    @Query("SELECT f.releaseYear, COUNT(f) FROM Film f GROUP BY f.releaseYear ORDER BY f.releaseYear")
    List<Object[]> countFilmsGroupedByReleaseYear();
}
