package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor // Required by JPA
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer paymentId;

    // Kept as Integer to prevent "Unknown Entity" crashes until you build the Customer entity
    @Column(name = "customer_id")
    private Integer customerId;

    // Kept as Integer to prevent crashes until you build the Staff entity
    @Column(name = "staff_id")
    private Integer staffId;

    // Kept as Integer to prevent crashes until you build the Rental entity
    @Column(name = "rental_id")
    private Integer rentalId;

    @Column(name = "amount", precision = 5, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}