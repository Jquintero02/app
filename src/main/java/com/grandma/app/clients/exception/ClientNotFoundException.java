package com.grandma.app.clients.exceptions;

public class ClientNotFoundException extends RuntimeException{
    private final String message;

    public ClientNotFoundException(String message){
        this.message = message;
    }
}
