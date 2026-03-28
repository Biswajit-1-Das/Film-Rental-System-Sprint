package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Byte> {

    Optional<Category> findByNameIgnoreCase(String name);
}
