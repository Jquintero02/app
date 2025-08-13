package com.grandma.app.products.exception;

public class ProductNotFoundException extends RuntimeException{
    private final String message;

    public ProductNotFoundException(String message){
        this.message = message;
    }
}
