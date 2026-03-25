package com.logincontroller.filmrentalsystem.model;

import java.time.LocalDateTime;


import jakarta.persistence.*;
        import lombok.*;

        import java.time.LocalDateTime;

@Entity
@Table(name = "customer")

@Data               // getters + setters + toString
@NoArgsConstructor  // default constructor
@AllArgsConstructor // full constructor
@Builder            // builder pattern

public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "store_id")
    private int storeId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    @Column(name = "address_id")
    private int addressId;

    private int active;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}