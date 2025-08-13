package com.grandma.app.clients.exception;

public class ClientNotFoundException extends RuntimeException{
    private final String message;

    public ClientNotFoundException(String message){
        this.message = message;
    }
}
