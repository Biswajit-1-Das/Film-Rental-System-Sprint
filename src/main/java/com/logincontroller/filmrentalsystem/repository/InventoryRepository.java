package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    List<Inventory> findByFilm_FilmId(Short filmId);

    List<Inventory> findByStore_StoreId(Byte storeId);

    List<Inventory> findByFilm_FilmIdAndStore_StoreId(Short filmId, Byte storeId);

    Byte store(Store store);
}
