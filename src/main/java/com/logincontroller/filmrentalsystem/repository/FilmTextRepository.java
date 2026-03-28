package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Film_Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmTextRepository extends JpaRepository<Film_Text, Short> {
}
