package com.logincontroller.filmrentalsystem.repository;

import com.logincontroller.filmrentalsystem.model.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Short> {

    List<Payments> findByCustomerCustomerId(Short customerId);

    List<Payments> findByStaffStaffId(Byte staffId);

    List<Payments> findByRentalRentalId(Integer rentalId);

    @Query(value = "SELECT DATE(payment_date) AS d, SUM(amount) AS amt FROM payment GROUP BY DATE(payment_date) ORDER BY d", nativeQuery = true)
    List<Object[]> revenueByDate();

    @Query(value = """
            SELECT DATE(p.payment_date) AS d, SUM(p.amount) AS amt
            FROM payment p
            JOIN customer c ON p.customer_id = c.customer_id
            WHERE c.store_id = :storeId
            GROUP BY DATE(p.payment_date)
            ORDER BY d
            """, nativeQuery = true)
    List<Object[]> revenueByDateForStore(@Param("storeId") byte storeId);

    @Query(value = """
            SELECT f.film_id, SUM(p.amount) AS revenue FROM payment p
            JOIN rental r ON p.rental_id = r.rental_id
            JOIN inventory i ON r.inventory_id = i.inventory_id
            JOIN film f ON i.film_id = f.film_id
            GROUP BY f.film_id
            ORDER BY revenue DESC
            """, nativeQuery = true)
    List<Object[]> revenueByFilm();

    @Query(value = """
            SELECT COALESCE(SUM(p.amount), 0) FROM payment p
            JOIN rental r ON p.rental_id = r.rental_id
            JOIN inventory i ON r.inventory_id = i.inventory_id
            WHERE i.film_id = :filmId
            """, nativeQuery = true)
    Object revenueTotalForFilm(@Param("filmId") short filmId);

    @Query(value = """
            SELECT f.film_id, SUM(p.amount) AS revenue FROM payment p
            JOIN rental r ON p.rental_id = r.rental_id
            JOIN inventory i ON r.inventory_id = i.inventory_id
            JOIN film f ON i.film_id = f.film_id
            WHERE i.store_id = :storeId
            GROUP BY f.film_id
            ORDER BY revenue DESC
            """, nativeQuery = true)
    List<Object[]> revenueByFilmsForStore(@Param("storeId") byte storeId);
}
