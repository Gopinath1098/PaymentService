package com.store.payment.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payments")
@Data
public class Payment {

    @Id
    @GeneratedValue
    @UuidGenerator
    @Column(name = "payment_id", unique = true, nullable = false)
    private String paymentId;   // Razorpay payment ID (pay_xxx)

    @Column(name = "order_id", nullable = false)
    private String orderId;     // Your internal order reference

    @Column(name = "rzp_order_id", nullable = false)
    private String rzpOrderId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "amount",nullable = false)
    private Double amount;

    @Column(name = "currency", length = 10)
    private String currency = "INR";

    @Column(name = "payment_method")
    private String paymentMethod = "UPI"; // Card, UPI, Cash, etc.

    @Column(name = "payment_status")
    private String paymentStatus = "UNPAID"; // PAID UNPAID

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}

