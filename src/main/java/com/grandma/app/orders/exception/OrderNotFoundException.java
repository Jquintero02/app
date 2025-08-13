package com.grandma.app.orders.exception;

public class OrderNotFoundException extends RuntimeException{
    private final String message;

    public OrderNotFoundException(String message){
        this.message = message;
    }
}
