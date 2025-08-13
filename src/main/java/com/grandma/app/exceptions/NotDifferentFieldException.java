package com.grandma.app.exceptions;

public class NotDifferentFieldException extends RuntimeException{
    private final String message;

    public NotDifferentFieldException(String message) {
        this.message = message;
    }
}
