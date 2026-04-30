package com.airline.airlinebackend.common.response;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class apiResponse {
    private HttpStatus status;
    private String message;
    private Object data;

    public apiResponse(HttpStatus status,String message,Object data){
        this.status=status;
        this.message=message;
        this.data=data;
    }
}
