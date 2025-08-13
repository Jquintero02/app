package com.grandma.app.orders.exception;

import com.grandma.app.exceptions.GlobalExceptionHandler;
import com.grandma.app.exceptions.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

public class OrdersExceptionHandler extends GlobalExceptionHandler {

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResponseDto> handle(OrderNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDto(
                        HttpStatus.NOT_FOUND.toString(),
                        LocalDateTime.now(),
                        exception.getMessage(),
                        "OrderNotFoundException"));
    }
}