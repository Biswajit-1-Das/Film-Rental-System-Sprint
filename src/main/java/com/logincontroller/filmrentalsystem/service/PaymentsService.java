package com.logincontroller.filmrentalsystem.service;

import com.logincontroller.filmrentalsystem.model.Payments;
import com.logincontroller.filmrentalsystem.repository.PaymentsRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentsService {

    private final PaymentsRepository paymentsRepository;

    public PaymentsService(PaymentsRepository paymentsRepository) {
        this.paymentsRepository = paymentsRepository;
    }

    public List<Payments> getAllPayments() {
        return paymentsRepository.findAll();
    }

    public Payments getPaymentById(Integer id) {
        return paymentsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
    }

    public Payments createPayment(Payments payment) {
        // Automatically set the timestamps when creating a new record
        payment.setPaymentDate(LocalDateTime.now());
        payment.setLastUpdate(LocalDateTime.now());
        return paymentsRepository.save(payment);
    }

    public Payments updatePayment(Integer id, Payments paymentDetails) {
        Payments existingPayment = getPaymentById(id);
        
        existingPayment.setCustomerId(paymentDetails.getCustomerId());
        existingPayment.setStaffId(paymentDetails.getStaffId());
        existingPayment.setRentalId(paymentDetails.getRentalId());
        existingPayment.setAmount(paymentDetails.getAmount());
        
        existingPayment.setLastUpdate(LocalDateTime.now());
        
        return paymentsRepository.save(existingPayment);
    }

    public Payments patchPayment(Integer id, Payments paymentUpdates) {
        Payments existingPayment = getPaymentById(id);
        
        if (paymentUpdates.getCustomerId() != null) {
            existingPayment.setCustomerId(paymentUpdates.getCustomerId());
        }
        if (paymentUpdates.getStaffId() != null) {
            existingPayment.setStaffId(paymentUpdates.getStaffId());
        }
        if (paymentUpdates.getRentalId() != null) {
            existingPayment.setRentalId(paymentUpdates.getRentalId());
        }
        if (paymentUpdates.getAmount() != null) {
            existingPayment.setAmount(paymentUpdates.getAmount());
        }
        
        existingPayment.setLastUpdate(LocalDateTime.now());
        return paymentsRepository.save(existingPayment);
    }

    public void deletePayment(Integer id) {
        paymentsRepository.deleteById(id);
    }
}