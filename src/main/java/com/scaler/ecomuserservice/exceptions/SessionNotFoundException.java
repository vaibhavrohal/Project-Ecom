package com.scaler.ecomuserservice.exceptions;

public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException(String message){
        super(message);
    }
}
