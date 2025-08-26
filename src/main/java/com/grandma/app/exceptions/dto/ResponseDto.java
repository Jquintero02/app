package com.grandma.app.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ResponseDto {
    private String code;
    private LocalDateTime timestamp;
    private String description;
    private String exception;
}
