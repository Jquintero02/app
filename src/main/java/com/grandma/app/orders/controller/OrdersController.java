package com.grandma.app.orders.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.service.OrdersServiceImpl;

import jakarta.validation.Valid;

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

    @PutMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<?> updateOrder(@PathVariable UUID uuid, @PathVariable LocalDateTime timestamp) {
        if (uuid == null || timestamp == null) {
            throw new IllegalArgumentException("UUID and timestamp cannot be null");
        }

        return ResponseEntity.status(HttpStatus.OK).body(service.updateOrder(uuid, timestamp));
    }
}
