package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor // Required by JPA
@AllArgsConstructor
@Entity
@Table(name = "store")
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "manager_staff_id")
    private Integer managerStaffId;

    @Column(name = "address_id")
    private Integer addressId;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}