package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;




import jakarta.persistence.*;
        import lombok.*;

        import java.time.LocalDateTime;

@Entity
@Table(name = "rental")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "rental_date")
    private LocalDateTime rentalDate;

    // ✅ FK as normal field (simple way)
    @Column(name = "inventory_id")
    private int inventoryId;

    // ✅ RELATIONSHIP (IMPORTANT 🔥)
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "return_date")
    private LocalDateTime returnDate;

    @Column(name = "staff_id")
    private int staffId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}