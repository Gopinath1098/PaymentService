package com.store.payment.controller;

import com.razorpay.Order;
import com.store.payment.service.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/initiate")
    public String initiatePayment(@RequestParam Double amount,
                                  @RequestParam String currency,
                                  @RequestParam String receiptId) throws Exception {
        Order order = paymentService.createOrder(amount, currency, receiptId);
        return order.toString(); // send order JSON to frontend
    }

    @PostMapping("/capture")
    public String capturePayment(@RequestParam String paymentId,
                                 @RequestParam Double amount) throws Exception {
        paymentService.capturePayment(paymentId, amount);
        return "Payment captured successfully!";
    }
}
