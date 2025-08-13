package com.grandma.app.exceptions;

import com.grandma.app.exceptions.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;

@Component
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotDifferentFieldException.class)
    public ResponseEntity<ResponseDto> handleException(NotDifferentFieldException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseDto(
                        HttpStatus.CONFLICT.toString(),
                        LocalDateTime.now(),
                        exception.getMessage(),
                        "NotDifferentFieldException"
                )
        );
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ResponseDto> handleException(NullPointerException exception){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new ResponseDto(
                        HttpStatus.CONFLICT.toString(),
                        LocalDateTime.now(),
                        exception.getMessage(),
                        "NullPointerException"
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto> handleException(MethodArgumentNotValidException exception){
        var errors = new HashMap<String, String>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            var fieldName = ((FieldError) error).getField();
            var errorMessage = error.getDefaultMessage();

            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now(),
                        errors.toString(),
                        "MethodArgumentNotValidException"
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto> handleException(Exception exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseDto(
                        HttpStatus.BAD_REQUEST.toString(),
                        LocalDateTime.now(),
                        "Error general del servidor",
                        "Exception"
                )
        );
    }
}
