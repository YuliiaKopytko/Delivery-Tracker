package com.kopytko.orderservice.kafka;

import com.kopytko.common.events.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    @Value("${order.topic}")
    private String topic;

    public void sendOrder(OrderCreatedEvent event) {
        kafkaTemplate.send(topic, event.getOrderId(), event);
    }
}
