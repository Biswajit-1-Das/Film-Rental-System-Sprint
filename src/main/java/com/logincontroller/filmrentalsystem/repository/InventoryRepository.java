package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {
    
    // Find all physical copies of a specific movie
    List<Inventory> findByFilm_FilmId(Short filmId);

    // Find all physical copies sitting in a specific store
    List<Inventory> findByStore_StoreId(Integer storeId);
    
    // Find a specific movie at a specific store
    List<Inventory> findByFilm_FilmIdAndStore_StoreId(Short filmId, Integer storeId);
}