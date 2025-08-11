package com.grandma.app.dto;

import java.time.LocalDateTime;

public class ResponseDto {

    public ResponseDto(String code, LocalDateTime timestamp, String message, String type) {
        this.code = code;
        this.timestamp = timestamp;
        this.message = message;
        this.type = type;
    }

    private String code;
    private LocalDateTime timestamp;
    private String message;
    private String type;

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
