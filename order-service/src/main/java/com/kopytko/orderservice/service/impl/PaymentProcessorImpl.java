package com.kopytko.orderservice.service.impl;

import com.kopytko.orderservice.service.OrderService;
import com.kopytko.orderservice.service.PaymentProcessor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentProcessorImpl implements PaymentProcessor {
    private final OrderService orderService;

    @Override
    public void processPayment(String orderId, String status) {
        orderService.updateStatus(orderId, status);
    }
}
