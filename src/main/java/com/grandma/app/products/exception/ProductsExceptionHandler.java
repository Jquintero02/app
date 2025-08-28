package com.grandma.app.products.exception;

import com.grandma.app.exceptions.GlobalExceptionHandler;
import com.grandma.app.exceptions.dto.ResponseDto;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Primary
public class ProductsExceptionHandler extends GlobalExceptionHandler {

        @ExceptionHandler(ProductAlreadyExistsException.class)
        public ResponseEntity<ResponseDto> handle(ProductAlreadyExistsException exception) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body(
                                new ResponseDto(
                                                HttpStatus.CONFLICT.toString(),
                                                LocalDateTime.now(),
                                                exception.getMessage(),
                                                "ProductAlreadyExistsException"));
        }

        @ExceptionHandler(ProductNotFoundException.class)
        public ResponseEntity<ResponseDto> handle(ProductNotFoundException exception) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                new ResponseDto(
                                                HttpStatus.NOT_FOUND.toString(),
                                                LocalDateTime.now(),
                                                exception.getMessage(),
                                                "ProductNotFoundException"));
        }
}
