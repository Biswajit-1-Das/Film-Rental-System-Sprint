package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    /**
     * Find all inventory items for a specific film
     */
    List<Inventory> findByFilmFilmId(Integer filmId);

    /**
     * Find all inventory items for a specific store
     */
    List<Inventory> findByStoreStoreId(Integer storeId);

    /**
     * Find inventory items for a specific film at a specific store
     */
    List<Inventory> findByFilmFilmIdAndStoreStoreId(Integer filmId, Integer storeId);

    /**
     * Find available inventory for a film at a specific store
     * An inventory item is available if it has no active rental (return_date is NULL)
     */
    @Query("SELECT i FROM Inventory i " +
            "WHERE i.film.filmId = :filmId " +
            "AND i.store.storeId = :storeId " +
            "AND NOT EXISTS (" +
            "  SELECT r FROM Rental r " +
            "  WHERE r.inventory.inventoryId = i.inventoryId " +
            "  AND r.returnDate IS NULL" +
            ")")
    List<Inventory> findAvailableByFilmAndStore(@Param("filmId") Integer filmId,
                                                @Param("storeId") Integer storeId);

    /**
     * Find first available inventory item for a film at a store
     */
    @Query("SELECT i FROM Inventory i " +
            "WHERE i.film.filmId = :filmId " +
            "AND i.store.storeId = :storeId " +
            "AND NOT EXISTS (" +
            "  SELECT r FROM Rental r " +
            "  WHERE r.inventory.inventoryId = i.inventoryId " +
            "  AND r.returnDate IS NULL" +
            ") " +
            "ORDER BY i.inventoryId ASC")
    Optional<Inventory> findFirstAvailableByFilmAndStore(@Param("filmId") Integer filmId,
                                                         @Param("storeId") Integer storeId);

    /**
     * Count total inventory items for a film
     */
    Long countByFilmFilmId(Integer filmId);

    /**
     * Count available inventory for a film at a specific store
     */
    @Query("SELECT COUNT(i) FROM Inventory i " +
            "WHERE i.film.filmId = :filmId " +
            "AND i.store.storeId = :storeId " +
            "AND NOT EXISTS (" +
            "  SELECT r FROM Rental r " +
            "  WHERE r.inventory.inventoryId = i.inventoryId " +
            "  AND r.returnDate IS NULL" +
            ")")
    Long countAvailableByFilmAndStore(@Param("filmId") Integer filmId,
                                      @Param("storeId") Integer storeId);

    /**
     * Find all rented inventory items (no return date)
     */
    @Query("SELECT DISTINCT i FROM Inventory i " +
            "JOIN i.rentals r " +
            "WHERE r.returnDate IS NULL")
    List<Inventory> findAllCurrentlyRented();

    /**
     * Find inventory with film and store details
     */
    @Query("SELECT i FROM Inventory i " +
            "LEFT JOIN FETCH i.film " +
            "LEFT JOIN FETCH i.store " +
            "WHERE i.inventoryId = :id")
    Optional<Inventory> findByIdWithDetails(@Param("id") Integer id);

    /**
     * Check if specific inventory item is available
     */
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN false ELSE true END " +
            "FROM Rental r " +
            "WHERE r.inventory.inventoryId = :inventoryId " +
            "AND r.returnDate IS NULL")
    boolean isInventoryAvailable(@Param("inventoryId") Integer inventoryId);
}