package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "inventory")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inventory_id")
    private Integer inventoryId;

    // Many-to-One: Many inventory items belong to one Film
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    // Many-to-One: Many inventory items belong to one Store
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Stores store;

    @Column(name = "last_update", nullable = false,
            insertable = false, updatable = false)
    private LocalDateTime lastUpdate;

    // Relationship: One Inventory item can have many Rentals
    // This is the inverse side - Rental entity owns the relationship
    @OneToMany(mappedBy = "inventory", fetch = FetchType.LAZY)
    private Set<Rental> rentals = new HashSet<>();

    // Helper method: Check if this inventory item is currently rented
    public boolean isCurrentlyRented() {
        if (rentals == null || rentals.isEmpty()) {
            return false;
        }
        return rentals.stream()
                .anyMatch(rental -> rental.getReturnDate() == null);
    }

    // Helper method: Get current rental if exists
    public Rental getCurrentRental() {
        if (rentals == null || rentals.isEmpty()) {
            return null;
        }
        return rentals.stream()
                .filter(rental -> rental.getReturnDate() == null)
                .findFirst()
                .orElse(null);
    }

    // Helper method: Check if available for rent
    public boolean isAvailable() {
        return !isCurrentlyRented();
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "inventoryId=" + inventoryId +
                ", filmId=" + (film != null ? film.getFilmId() : null) +
                ", storeId=" + (store != null ? store.getStoreId() : null) +
                ", available=" + isAvailable() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Inventory)) return false;
        Inventory inventory = (Inventory) o;
        return inventoryId != null && inventoryId.equals(inventory.inventoryId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
