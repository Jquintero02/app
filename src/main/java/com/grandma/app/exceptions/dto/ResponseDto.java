package com.grandma.app.exceptions.dto;

import java.time.LocalDateTime;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
}
