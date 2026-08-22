package com.store.payment.service;

import com.store.payment.dto.PaymentTemplatetDTO;
import com.store.payment.entity.Payment;
import com.store.payment.repository.PaymentRepo;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PaymentService {

    private final RazorpayClient razorpayClient;

    private PaymentRepo repo;

    public PaymentService(@Value("${razorpay.key_id}") String keyId,
                          @Value("${razorpay.secret}") String secret, PaymentRepo repo) throws Exception {
        this.razorpayClient = new RazorpayClient(keyId, secret);
        this.repo = repo;
    }

    // Create Razorpay order
    public Order createOrder(PaymentTemplatetDTO paymentTemplatetDTO) throws Exception {
        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", paymentTemplatetDTO.getAmount()); // amount in paise
        orderRequest.put("currency","INR");
        orderRequest.put("receipt", LocalDate.now().toString()+ LocalTime.now().toString());
        orderRequest.put("payment_capture", 1);
        Order order = razorpayClient.orders.create(orderRequest);
        Payment payment = new Payment();
        payment.setAmount(paymentTemplatetDTO.getAmount());
        payment.setCurrency(paymentTemplatetDTO.getCurrency());
        payment.setCustomerEmail(paymentTemplatetDTO.getCustomerEmail());
        payment.setCustomerName(payment.getCustomerName());
        payment.setPaymentMethod(payment.getPaymentMethod());
        payment.setPaymentStatus(payment.getPaymentStatus());
        payment.setOrderId(paymentTemplatetDTO.getOrderId());
        payment.setRzpOrderId(order.get("id"));
        this.repo.save(payment);
        return order;
    }

    // Capture payment (optional if auto-capture is disabled)
    public void capturePayment(String paymentId, Double amount) throws Exception {
        JSONObject captureRequest = new JSONObject();
        captureRequest.put("amount", amount.intValue() * 100); // amount in paise
        captureRequest.put("currency", "INR"); // optional, defaults to INR
        razorpayClient.payments.capture(paymentId, captureRequest);
    }
}

