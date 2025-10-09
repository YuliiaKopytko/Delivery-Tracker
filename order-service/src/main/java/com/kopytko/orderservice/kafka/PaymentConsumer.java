package com.kopytko.orderservice.kafka;

import com.kopytko.common.events.PaymentCreatedEvent;
import com.kopytko.orderservice.service.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentProcessor paymentProcessor;

    @KafkaListener(topics = "${payment.topic}", groupId = "order-service")
    public void consume(PaymentCreatedEvent event) {
        log.info("Received PaymentCreatedEvent");
        paymentProcessor.processPayment(event.getOrderId(), event.getPaymentStatus());
    }

}
