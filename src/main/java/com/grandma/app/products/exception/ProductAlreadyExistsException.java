package com.grandma.app.products.exception;

public class ProductAlreadyExistsException extends RuntimeException{
    private final String message;

    public ProductAlreadyExistsException(String message){
        this.message = message;
    }
}
