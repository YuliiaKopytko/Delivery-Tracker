package com.kopytko.paymentservice.kafka;

import com.kopytko.common.events.OrderCreatedEvent;
import com.kopytko.common.events.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentConsumer {
    private final PaymentProducer paymentProducer;

    @KafkaListener(topics = "${order.topic}", groupId = "payment-service")
    public void consume(OrderCreatedEvent event) throws InterruptedException {
        log.info("Processing payment for orderId={} product={} quantity={}",
                event.getOrderId(),
                event.getProduct(),
                event.getQuantity()
        );

        PaymentCreatedEvent paymentProcessingEvent = PaymentCreatedEvent.builder()
                .orderId(event.getOrderId())
                .paymentStatus("Processing")
                .build();

        paymentProducer.sendPayment(paymentProcessingEvent);


        Thread.sleep(5000);

        PaymentCreatedEvent paymentSuccess = PaymentCreatedEvent.builder()
                .orderId(event.getOrderId())
                .paymentStatus("Success")
                .build();

        paymentProducer.sendPayment(paymentSuccess);
        log.info("✅ Payment successful for order {}", event.getOrderId());
    }
}
