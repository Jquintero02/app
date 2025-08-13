package com.grandma.app.orders.service;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.entity.OrderEntity;

import java.time.LocalDateTime;
import java.util.UUID;

public interface OrdersService {
    OrderEntity createOrder(OrderDto order);

    OrderEntity updateOrder(UUID uuid, LocalDateTime timestamp);
}
