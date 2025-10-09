package com.kopytko.orderservice.service;

import com.kopytko.orderservice.entity.Order;

public interface OrderService {

    Order create(String product, Integer quantity);

    Order findById(String id);

    void updateStatus(String orderId, String status);

}
