package com.store.payment.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaptureRequestDTO {

    private String paymentId;
    private Double amount;
    private String currency = "INR"; // default to INR

}
