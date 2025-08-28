package com.grandma.app.orders.service;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.dto.OrderDtoResponse;

import java.time.LocalDateTime;

public interface OrdersService {
    OrderDtoResponse createOrder(OrderDto orderDto);

    OrderDtoResponse updateOrder(String uuid, LocalDateTime timestamp);
}
