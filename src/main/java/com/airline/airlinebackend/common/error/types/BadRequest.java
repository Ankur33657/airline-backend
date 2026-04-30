package com.airline.airlinebackend.common.error.types;

public class BadRequest extends  RuntimeException{
    public BadRequest(String message){
        super(message);
    }

}
