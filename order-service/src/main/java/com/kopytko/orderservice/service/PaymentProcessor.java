package com.kopytko.orderservice.service;

public interface PaymentProcessor {

    void processPayment(String orderId, String status);
}
