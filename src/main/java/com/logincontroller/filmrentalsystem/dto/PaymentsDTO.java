package com.logincontroller.filmrentalsystem.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class PaymentsDTO {
    private Short paymentId;
    private BigDecimal amount;
    private LocalDateTime paymentDate;
    private Integer rentalId;
    private Short customerId;
    private Byte staffId;
    private LocalDateTime lastUpdate;
}
