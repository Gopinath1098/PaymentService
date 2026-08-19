package com.store.payment.service;

import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;

    public PaymentService(@Value("${razorpay.key_id}") String keyId,
                          @Value("${razorpay.secret}") String secret) throws Exception {
        this.razorpayClient = new RazorpayClient(keyId, secret);
    }

    // Create Razorpay order
    public Order createOrder(Double amount, String currency, String receiptId) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount.intValue() * 100); // amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receiptId);
        orderRequest.put("payment_capture", 1);

        return razorpayClient.orders.create(orderRequest);
    }

    // Capture payment (optional if auto-capture is disabled)
    public void capturePayment(String paymentId, Double amount) throws Exception {
        JSONObject captureRequest = new JSONObject();
        captureRequest.put("amount", amount.intValue() * 100); // amount in paise
        captureRequest.put("currency", "INR"); // optional, defaults to INR
        razorpayClient.payments.capture(paymentId, captureRequest);
    }
}

