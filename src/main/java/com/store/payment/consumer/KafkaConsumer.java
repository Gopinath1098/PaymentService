package com.store.payment.consumer;

import com.store.payment.dto.PaymentTemplateDTO;
import com.store.payment.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Slf4j
public class KafkaConsumer {

    private PaymentService service;

    public KafkaConsumer(PaymentService service) {
        this.service = service;
    }

    @KafkaListener(
            topics = "${notification.topics.payment-notification}")
    public void consume(PaymentTemplateDTO event) throws IOException {

        log.info("Received Event");

        log.info("{}",event);

    }

}
