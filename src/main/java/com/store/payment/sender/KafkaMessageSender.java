package com.store.payment.sender;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.store.payment.dto.PaymentTemplatetDTO;

@Service
@Slf4j
public class KafkaMessageSender {
    @Value("${notification.topics.payment-notification}")
    private String paymentTopic;

    @Autowired
    private KafkaTemplate<String,PaymentTemplatetDTO> kafkaTemplate;

    public void sendPayment(PaymentTemplatetDTO payment) {
        kafkaTemplate.send(paymentTopic, payment);
        log.info("Order event sent: {}",payment);
    }
}

