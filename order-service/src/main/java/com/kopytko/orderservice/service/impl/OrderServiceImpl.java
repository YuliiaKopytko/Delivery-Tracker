package com.kopytko.orderservice.service.impl;

import com.kopytko.orderservice.entity.Order;
import com.kopytko.orderservice.repository.OrderRepository;
import com.kopytko.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order create(String product, Integer quantity) {
        Order order = Order.builder()
                .id(UUID.randomUUID().toString())
                .product(product)
                .quantity(quantity)
                .build();
        Order saved = orderRepository.save(order);
        log.info("Order created, id = {}", saved.getId());
        return saved;
    }

    @Override
    public Order findById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order with id="+ id+" doesn't exists"));
    }

    @Override
    public void updateStatus(String orderId, String status) {
        Order order = findById(orderId);
        order.setPaymentStatus(status);
        orderRepository.save(order);
        log.info("Order updated, id = {}", orderId);
    }


}
