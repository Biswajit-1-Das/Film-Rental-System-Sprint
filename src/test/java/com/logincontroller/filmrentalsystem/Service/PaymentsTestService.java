package com.logincontroller.filmrentalsystem.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.logincontroller.filmrentalsystem.dto.FilmRevenueDTO;
import com.logincontroller.filmrentalsystem.dto.PaymentsResponseDTO;
import com.logincontroller.filmrentalsystem.model.Customer;
import com.logincontroller.filmrentalsystem.model.Payments;
import com.logincontroller.filmrentalsystem.model.Rental;
import com.logincontroller.filmrentalsystem.model.Staff;
import com.logincontroller.filmrentalsystem.repository.CustomerRepository;
import com.logincontroller.filmrentalsystem.repository.PaymentsRepository;
import com.logincontroller.filmrentalsystem.repository.RentalRepository;
import com.logincontroller.filmrentalsystem.repository.StaffRepository;
import com.logincontroller.filmrentalsystem.service.PaymentsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class PaymentsTestService {

    @Mock
    PaymentsRepository paymentsRepository;

    @Mock
    CustomerRepository customerRepository;

    @Mock
    StaffRepository staffRepository;

    @Mock
    RentalRepository rentalRepository;

    @InjectMocks
    PaymentsService paymentsService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createPayment_setsRelationsAndReturnsDto() {
        Short customerId = 1;
        Byte staffId = 2;
        Integer rentalId = 3;

        Customer customer = new Customer();
        customer.setCustomerId(customerId);

        Staff staff = new Staff();
        staff.setStaffId(staffId);

        Rental rental = new Rental();
        rental.setRentalId(rentalId);

        Payments input = new Payments();
        input.setAmount(new BigDecimal("10.00"));

        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));
        when(staffRepository.findById(staffId)).thenReturn(Optional.of(staff));
        when(rentalRepository.findById(rentalId)).thenReturn(Optional.of(rental));

        Payments saved = new Payments();
        saved.setPaymentId((short) 99);
        saved.setAmount(new BigDecimal("10.00"));
        saved.setCustomer(customer);
        saved.setStaff(staff);
        saved.setRental(rental);
        saved.setLastUpdate(null);
        saved.setPaymentDate(null);

        when(paymentsRepository.save(any(Payments.class))).thenReturn(saved);

        PaymentsResponseDTO dto = paymentsService.createPayment(customerId, staffId, rentalId, input);

        assertEquals((short) 99, dto.getPaymentId());
        assertEquals(customerId, dto.getCustomerId());
        assertEquals(staffId, dto.getStaffId());
        assertEquals(rentalId, dto.getRentalId());

        verify(paymentsRepository).save(any(Payments.class));
    }

    @Test
    public void revenueTotalForFilm_returnsBigDecimalOrZero() {
        Short filmId = 1;
        BigDecimal revenue = new BigDecimal("123.45");

        when(paymentsRepository.revenueTotalForFilm(filmId)).thenReturn(revenue);

        BigDecimal out = paymentsService.revenueTotalForFilm(filmId);

        assertEquals(revenue, out);
        verify(paymentsRepository).revenueTotalForFilm(filmId);
    }

    @Test
    public void revenueByFilm_mapsAggregateRowsToDtos() {
        Object[] row = new Object[] { 5, new BigDecimal("50.00") };
        when(paymentsRepository.revenueByFilm()).thenReturn(List.<Object[]>of(row));

        List<FilmRevenueDTO> out = paymentsService.revenueByFilm();

        assertEquals(1, out.size());
        assertEquals((short) 5, out.get(0).getFilmId());
        assertEquals(new BigDecimal("50.00"), out.get(0).getRevenue());
        verify(paymentsRepository).revenueByFilm();
    }
}

