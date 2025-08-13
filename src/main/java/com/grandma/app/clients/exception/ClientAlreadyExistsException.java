package com.grandma.app.clients.exception;

public class ClientAlreadyExistsException extends RuntimeException {
    private final String message;

    public ClientAlreadyExistsException(String message){
        this.message = message;
    }
}
