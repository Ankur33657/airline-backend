package com.airline.airlinebackend.common.error;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private LocalDateTime timestamp;

    public ErrorResponse(HttpStatus status,String message,LocalDateTime now){
        this.status=status;
        this.message=message;
        this.timestamp=now;
    }
}
