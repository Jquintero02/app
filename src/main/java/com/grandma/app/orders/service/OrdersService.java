package com.grandma.app.orders.service;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.entity.OrderEntity;

import java.time.LocalDateTime;

public interface OrdersService {
    OrderEntity createOrder(OrderDto order);

    OrderEntity updateOrder(String uuid, LocalDateTime timestamp);
}
