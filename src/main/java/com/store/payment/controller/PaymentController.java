package com.store.payment.controller;

import com.store.payment.dto.PaymentTemplatetDTO;
import com.store.payment.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public ResponseEntity<String> initiatePayment(@RequestBody PaymentTemplatetDTO paymentDTO) throws Exception {
        paymentService.createOrder(paymentDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("ORDER CAPTURED"); // send order JSON to frontend
    }

    @PostMapping("/capture")
    public String capturePayment(@RequestParam String paymentId,
                                 @RequestParam Double amount) throws Exception {
        paymentService.capturePayment(paymentId, amount);
        return "Payment captured successfully!";
    }
}
