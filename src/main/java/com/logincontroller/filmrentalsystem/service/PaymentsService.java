package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.dto.DateRevenueDTO;
import com.logincontroller.filmrentalsystem.dto.FilmRevenueDTO;
import com.logincontroller.filmrentalsystem.dto.PaymentsResponseDTO;
import com.logincontroller.filmrentalsystem.model.*;
import com.logincontroller.filmrentalsystem.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;
    private final CustomerRepository customerRepository;
    private final StaffRepository staffRepository;
    private final RentalRepository rentalRepository;

    private PaymentsResponseDTO toResponseDTO(Payments p) {
        PaymentsResponseDTO dto = new PaymentsResponseDTO();
        dto.setPaymentId(p.getPaymentId());
        dto.setAmount(p.getAmount());
        dto.setPaymentDate(p.getPaymentDate());
        dto.setLastUpdate(p.getLastUpdate());
        if (p.getRental() != null) {
            dto.setRentalId(p.getRental().getRentalId());
        }
        if (p.getCustomer() != null) {
            dto.setCustomerId(p.getCustomer().getCustomerId());
        }
        if (p.getStaff() != null) {
            dto.setStaffId(p.getStaff().getStaffId());
        }
        return dto;
    }

    @Transactional
    public PaymentsResponseDTO createPayment(Short customerId, Byte staffId,
                                             Integer rentalId, Payments payment) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        payment.setCustomer(customer);
        payment.setStaff(staff);
        payment.setRental(rental);

        payment.setPaymentDate(LocalDateTime.now());
        payment.setLastUpdate(LocalDateTime.now());

        return toResponseDTO(paymentsRepository.save(payment));
    }

    @Transactional(readOnly = true)
    public PaymentsResponseDTO getPaymentById(Short id) {
        return toResponseDTO(paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id)));
    }

    @Transactional(readOnly = true)
    public List<PaymentsResponseDTO> getAllPayments() {
        return paymentsRepository.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PaymentsResponseDTO> getPaymentsByCustomer(Short customerId) {
//        return paymentsRepository.findByCustomerCustomerId(customerId)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
        List<Payments> payments = paymentsRepository.findByCustomerCustomerId(customerId);
        List<PaymentsResponseDTO> result = new ArrayList<>();
        for(Payments p : payments)
        {
            result.add(toResponseDTO(p));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<PaymentsResponseDTO> getPaymentsByStaff(Byte staffId) {
//        return paymentsRepository.findByStaffStaffId(staffId)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
        List<Payments> payments = paymentsRepository.findByStaffStaffId(staffId);
        List<PaymentsResponseDTO> result = new ArrayList<>();
        for(Payments p : payments)
        {
            result.add(toResponseDTO(p));
        }
        return result;
    }

    @Transactional(readOnly = true)
    public List<PaymentsResponseDTO> getPaymentsByRental(Integer rentalId) {
//        return paymentsRepository.findByRentalRentalId(rentalId)
//                .stream()
//                .map(this::toResponseDTO)
//                .collect(Collectors.toList());
        List<Payments> payments = paymentsRepository.findByRentalRentalId(rentalId);
        List<PaymentsResponseDTO> result = new ArrayList<>();
        for(Payments p : payments)
        {
            result.add(toResponseDTO(p));
        }
        return result;
    }

    @Transactional
    public PaymentsResponseDTO updatePayment(Short id, Payments updatedPayment,
                                             Short customerId, Byte staffId, Integer rentalId) {

        Payments existing = paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Staff staff = staffRepository.findById(staffId)
                .orElseThrow(() -> new RuntimeException("Staff not found"));

        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        existing.setAmount(updatedPayment.getAmount());
        existing.setCustomer(customer);
        existing.setStaff(staff);
        existing.setRental(rental);
        existing.setPaymentDate(updatedPayment.getPaymentDate());
        existing.setLastUpdate(LocalDateTime.now());

        return toResponseDTO(paymentsRepository.save(existing));
    }

    private static LocalDate toLocalDate(Object o) {
        if (o == null) {
            return null;
        }
        if (o instanceof java.sql.Date) {
            return ((java.sql.Date) o).toLocalDate();
        }
        if (o instanceof LocalDate) {
            return (LocalDate) o;
        }
        throw new IllegalArgumentException("Unsupported date type: " + o.getClass());
    }

    @Transactional(readOnly = true)
    public List<DateRevenueDTO> revenueByDate() {
        List<DateRevenueDTO> out = new ArrayList<>();
        for (Object[] row : paymentsRepository.revenueByDate()) {
            LocalDate d = toLocalDate(row[0]);
            BigDecimal amt = (BigDecimal) row[1];
            out.add(new DateRevenueDTO(d, amt));
        }
        return out;
    }

    @Transactional(readOnly = true)
    public List<DateRevenueDTO> revenueByDateForStore(Byte storeId) {
        List<DateRevenueDTO> out = new ArrayList<>();
        for (Object[] row : paymentsRepository.revenueByDateForStore(storeId)) {
            LocalDate d = toLocalDate(row[0]);
            BigDecimal amt = (BigDecimal) row[1];
            out.add(new DateRevenueDTO(d, amt));
        }
        return out;
    }

    @Transactional(readOnly = true)
    public List<FilmRevenueDTO> revenueByFilm() {
        List<FilmRevenueDTO> out = new ArrayList<>();
        for (Object[] row : paymentsRepository.revenueByFilm()) {
            Short filmId = ((Number) row[0]).shortValue();
            BigDecimal revenue = (BigDecimal) row[1];
            out.add(new FilmRevenueDTO(filmId, revenue));
        }
        return out;
    }

    @Transactional(readOnly = true)
    public BigDecimal revenueTotalForFilm(Short filmId) {
        Object o = paymentsRepository.revenueTotalForFilm(filmId);
        if (o == null) {
            return BigDecimal.ZERO;
        }
        if (o instanceof BigDecimal) {
            return (BigDecimal) o;
        }
        return BigDecimal.valueOf(((Number) o).doubleValue());
    }

    @Transactional(readOnly = true)
    public List<FilmRevenueDTO> revenueByFilmsForStore(Byte storeId) {
        List<FilmRevenueDTO> out = new ArrayList<>();
        for (Object[] row : paymentsRepository.revenueByFilmsForStore(storeId)) {
            Short filmId = ((Number) row[0]).shortValue();
            BigDecimal revenue = (BigDecimal) row[1];
            out.add(new FilmRevenueDTO(filmId, revenue));
        }
        return out;
    }
}
