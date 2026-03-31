package com.logincontroller.filmrentalsystem.Controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logincontroller.filmrentalsystem.controller.PaymentController;
import com.logincontroller.filmrentalsystem.dto.FilmRevenueDTO;
import com.logincontroller.filmrentalsystem.dto.PaymentsResponseDTO;
import com.logincontroller.filmrentalsystem.model.Payments;
import com.logincontroller.filmrentalsystem.service.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public class PaymentTestController {

    @Mock
    PaymentsService paymentsService;

    PaymentController paymentController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        paymentController = new PaymentController(paymentsService);
    }

    @Test
    public void addPayment_delegatesToService() {
        Short customerId = 1;
        Byte staffId = 2;
        Integer rentalId = 3;

        Payments input = new Payments();
        input.setAmount(new BigDecimal("10.00"));

        PaymentsResponseDTO expected = new PaymentsResponseDTO();
        expected.setCustomerId(customerId);
        expected.setStaffId(staffId);
        expected.setRentalId(rentalId);

        when(paymentsService.createPayment(customerId, staffId, rentalId, input)).thenReturn(expected);

        ResponseEntity<PaymentsResponseDTO> response = paymentController.addPayment(customerId, staffId, rentalId, input);

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(paymentsService).createPayment(customerId, staffId, rentalId, input);
    }

    @Test
    public void revenueFilmwise_delegatesToService() {
        List<FilmRevenueDTO> expected = List.of(new FilmRevenueDTO());
        when(paymentsService.revenueByFilm()).thenReturn(expected);

        ResponseEntity<List<FilmRevenueDTO>> response = paymentController.revenueFilmwise();

        assertEquals(200, response.getStatusCode().value());
        assertSame(expected, response.getBody());
        verify(paymentsService).revenueByFilm();
    }
}

