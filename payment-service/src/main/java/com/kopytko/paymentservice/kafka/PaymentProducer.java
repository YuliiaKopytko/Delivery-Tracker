package com.kopytko.paymentservice.kafka;

import com.kopytko.common.events.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentProducer {

    private final KafkaTemplate<String, PaymentCreatedEvent> kafkaTemplate;

    @Value("${payment.topic}")
    private String topic;

    public void sendPayment(PaymentCreatedEvent event) {
        this.kafkaTemplate.send(topic, event.getOrderId(), event);
    }

}
