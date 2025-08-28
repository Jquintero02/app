package com.grandma.app.orders.controller;

import com.grandma.app.orders.dto.OrderDto;
import com.grandma.app.orders.service.OrdersService;
import jakarta.validation.Valid;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ordersService.createOrder(orderDto));
    }

    @PatchMapping("/{uuid}/delivered/{timestamp}")
    public ResponseEntity<?> updateOrder(@PathVariable("uuid") String uuid,
            @PathVariable("timestamp") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.updateOrder(uuid, timestamp));
    }
}
