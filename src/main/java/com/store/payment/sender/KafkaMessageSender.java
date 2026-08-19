package com.store.payment.sender;


import com.store.payment.dto.PaymentTemplateDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaMessageSender {
    @Value("${notification.topics.payment-notification}")
    private String paymentTopic;

    @Autowired
    private KafkaTemplate<String, PaymentTemplateDTO> kafkaTemplate;

    public void sendPayment(PaymentTemplateDTO order) {
        kafkaTemplate.send(paymentTopic, order);
        log.info("Order event sent: {}",order);
    }
}

