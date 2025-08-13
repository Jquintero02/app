package com.grandma.app.exception;

public class NotDifferentFieldException extends RuntimeException{
    private final String message;

    public NotDifferentFieldException(String message) {
        this.message = message;
    }
}
