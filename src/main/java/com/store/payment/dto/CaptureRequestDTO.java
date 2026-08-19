package com.store.payment.dto;


import lombok.Data;

@Data
public class CaptureRequestDTO {

    private String paymentId;
    private Double amount;
    private String currency = "INR"; // default to INR

    // Constructors
    public CaptureRequestDTO() {}

    public CaptureRequestDTO(String paymentId, Double amount, String currency) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.currency = currency;
    }
}
