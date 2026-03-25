package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}