package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.FilmResponseDTO;
import com.logincontroller.filmrentalsystem.dto.RentalResponseDTO;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.model.Film;
import com.logincontroller.filmrentalsystem.model.Inventory;
import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.InventoryRepository;
import com.logincontroller.filmrentalsystem.repository.RentalRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import com.logincontroller.filmrentalsystem.service.FilmService;
import com.logincontroller.filmrentalsystem.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

public class RentalTestService {

    @Mock
    RentalRepository rentalRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    StaffRepository staffRepository;

    @Mock
    InventoryRepository inventoryRepository;

    @Mock
    FilmService filmService;

    @InjectMocks
    RentalService rentalService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createRental_wiresRelationsAndMapsDto() {
        Short customerId = 1;
        Byte staffId = 2;
        Integer inventoryId = 3;

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setFirstName("John");
        customer.setLastName("Doe");

        Staff staff = new Staff();
        staff.setStaffId(staffId);
        staff.setFirstName("Alice");
        staff.setLastName("Smith");

        Film film = new Film();
        film.setFilmId((short) 10);
        film.setTitle("Film");

        Inventory inventory = new Inventory();
        inventory.setInventoryId(inventoryId);
        inventory.setFilm(film);

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        when(inventoryRepository.findById(inventoryId)).thenReturn(Optional.of(inventory));

        Rental input = new Rental();
        Rental saved = new Rental();
        saved.setRentalId(99);
        saved.setInventory(inventory);
        saved.setCustomer(customer);
        saved.setStaff(staff);
        saved.setRentalDate(LocalDateTime.now());
        saved.setLastUpdate(LocalDateTime.now());

        when(rentalRepository.save(any(Rental.class))).thenReturn(saved);

        RentalResponseDTO dto = rentalService.createRental(customerId, staffId, inventoryId, input);

        assertEquals(99, dto.getRentalId());
        assertEquals(inventoryId, dto.getInventoryId());
        assertEquals((short) 10, dto.getFilmId());
        assertEquals("Film", dto.getFilmTitle());
        assertEquals(customerId, dto.getCustomerId());
        assertEquals("John Doe", dto.getCustomerName());
        assertEquals(staffId, dto.getStaffId());
        assertEquals("Alice Smith", dto.getStaffName());
        verify(rentalRepository).save(any(Rental.class));
    }

    @Test
    public void updateReturnDate_updatesReturnDateAndSaves() {
        Integer rentalId = 1;
        LocalDateTime returnDate = LocalDateTime.of(2026, 3, 30, 12, 0);

        Rental existing = new Rental();
        existing.setRentalId(rentalId);
        existing.setReturnDate(null);

        Rental saved = new Rental();
        saved.setRentalId(rentalId);
        saved.setReturnDate(returnDate);
        saved.setLastUpdate(LocalDateTime.now());

        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(existing));
        when(rentalRepository.save(any(Rental.class))).thenReturn(saved);

        RentalResponseDTO dto = rentalService.updateReturnDate(rentalId, returnDate);

        assertEquals(returnDate, dto.getReturnDate());
        verify(rentalRepository).save(any(Rental.class));
    }
}

