package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.RentalController;
import com.logincontroller.filmrentalsystem.dto.RentalResponseDTO;
import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.service.RentalService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public class RentalTestController {

    @Mock
    RentalService rentalService;

    RentalController rentalController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        rentalController = new RentalController(rentalService);
    }

    @Test
    public void addRental_delegatesToService() {
        Short customerId = 1;
        Byte staffId = 2;
        Integer inventoryId = 3;

        Rental input = new Rental();

        RentalResponseDTO expected = new RentalResponseDTO();
        expected.setRentalId(10);

        when(rentalService.createRental(customerId, staffId, inventoryId, input)).thenReturn(expected);

        ResponseEntity<RentalResponseDTO> response = rentalController.addRental(customerId, staffId, inventoryId, input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(rentalService).createRental(customerId, staffId, inventoryId, input);
    }

    @Test
    public void updateReturnDate_delegatesToService() {
        Integer rentalId = 1;
        LocalDateTime returnDate = LocalDateTime.of(2026, 3, 30, 12, 0);

        RentalResponseDTO expected = new RentalResponseDTO();
        expected.setRentalId(rentalId);
        expected.setReturnDate(returnDate);

        when(rentalService.updateReturnDate(rentalId, returnDate)).thenReturn(expected);

        ResponseEntity<RentalResponseDTO> response = rentalController.updateReturnDate(rentalId, returnDate);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(rentalService).updateReturnDate(rentalId, returnDate);
    }
}

