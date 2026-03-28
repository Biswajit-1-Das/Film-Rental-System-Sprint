package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Integer> {

    List<Rental> findByCustomerCustomerId(Short customerId);

    List<Rental> findByStaffStaffId(Byte staffId);

    List<Rental> findByInventoryInventoryId(Integer inventoryId);

    @Query(value = """
            SELECT f.film_id FROM rental r
            INNER JOIN inventory i ON r.inventory_id = i.inventory_id
            INNER JOIN film f ON i.film_id = f.film_id
            GROUP BY f.film_id
            ORDER BY COUNT(r.rental_id) DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Short> findTopTenFilmIdsByRentalCount();

    @Query(value = """
            SELECT f.film_id FROM rental r
            INNER JOIN inventory i ON r.inventory_id = i.inventory_id
            INNER JOIN film f ON i.film_id = f.film_id
            WHERE i.store_id = :storeId
            GROUP BY f.film_id
            ORDER BY COUNT(r.rental_id) DESC
            LIMIT 10
            """, nativeQuery = true)
    List<Short> findTopTenFilmIdsByRentalCountForStore(@Param("storeId") byte storeId);

    @Query("SELECT r FROM Rental r WHERE r.returnDate IS NULL AND r.inventory.store.storeId = :storeId")
    List<Rental> findDueRentalsByStoreId(@Param("storeId") Byte storeId);
}
