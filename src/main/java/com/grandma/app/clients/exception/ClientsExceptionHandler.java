package com.grandma.app.clients.exceptions;

import com.grandma.app.exceptions.dto.ResponseDto;
import com.grandma.app.exceptions.GlobalExceptionHandler;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
@Primary
public class ClientsExceptionHandler extends GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<ResponseDto> handle(ClientNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseDto(
                        HttpStatus.NOT_FOUND.toString(),
                        LocalDateTime.now(),
                        "Cliente no encontrado",
                        exception.getMessage()
                )
        );
    }
}
