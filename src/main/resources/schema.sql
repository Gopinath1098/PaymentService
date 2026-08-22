CREATE TABLE payments (
    payment_id VARCHAR(255) PRIMARY KEY,   -- Razorpay payment ID (pay_xxx)
    order_id VARCHAR(255) NOT NULL,            -- Internal order reference
    customer_name VARCHAR(255),
    customer_email VARCHAR(255),
    amount DOUBLE PRECISION NOT NULL,             -- Precision 15, scale 2
    currency VARCHAR(10) DEFAULT 'INR',
    payment_method VARCHAR(255),               -- Card, UPI, Wallet, etc.
    payment_status VARCHAR(50),                -- PAID / UNPAID
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NULL
);
