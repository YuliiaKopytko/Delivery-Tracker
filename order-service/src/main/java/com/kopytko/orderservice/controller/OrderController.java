package com.kopytko.orderservice.controller;

import com.kopytko.common.events.OrderCreatedEvent;
import com.kopytko.orderservice.entity.Order;
import com.kopytko.orderservice.kafka.OrderProducer;
import com.kopytko.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderProducer producer;

    @PostMapping
    public String createOrder(@RequestParam String product,
                              @RequestParam int quantity) {

        Order order = orderService.create(product, quantity);
        OrderCreatedEvent event = new OrderCreatedEvent(
                order.getId(),
                product,
                quantity
        );
        producer.sendOrder(event);
        return "Order sent: " + event.getOrderId();
    }
}
