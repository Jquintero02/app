package com.grandma.app.orders.controller;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.service.OrdersServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersServiceImpl service;

    public OrdersController(OrdersServiceImpl service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.createOrder(order));
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<?> updateOrder(@PathVariable String uuid, @PathVariable String timestamp) {
        if (uuid == null || timestamp == null) {
            throw new IllegalArgumentException("String and timestamp cannot be null");
        }

        LocalDateTime deliveredDate = LocalDateTime.parse(timestamp);

        return ResponseEntity.status(HttpStatus.OK).body(service.updateOrder(uuid, deliveredDate));
    }
}
