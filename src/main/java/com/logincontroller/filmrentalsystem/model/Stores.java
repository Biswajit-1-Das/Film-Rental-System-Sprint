package com.logincontroller.filmrentalsystem.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "store")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Stores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Integer storeId;

    // The manager of this store — references the Staff table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "manager_staff_id", nullable = false)
    private Staff managerStaff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Column(name = "last_update", nullable = false,
            insertable = false, updatable = false)
    private LocalDateTime lastUpdate;
}