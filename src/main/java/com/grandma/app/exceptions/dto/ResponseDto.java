package com.grandma.app.exceptions.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ResponseDto {
    public ResponseDto(String code, LocalDateTime timestamp, String description, String exception) {
        this.code = code;
        this.timestamp = timestamp;
        this.description = description;
        this.exception = exception;
    }

    private String code;
    private LocalDateTime timestamp;
    private String description;
    private String exception;
}
