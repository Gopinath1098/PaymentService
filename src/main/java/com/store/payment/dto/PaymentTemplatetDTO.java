package com.store.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentTemplatetDTO {

    private String paymentId;
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String currency;
    private Double amount;
    private String paymentMethod;

    // SUCCESS, FAILED
    private String paymentStatus;
}