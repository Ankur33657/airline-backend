package com.airline.airlinebackend.common.error.types;

public class NotFound extends RuntimeException{
    public NotFound(String message){
        super(message);
    }
}
