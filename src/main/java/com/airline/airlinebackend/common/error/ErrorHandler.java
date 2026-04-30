package com.airline.airlinebackend.common.error;


import com.airline.airlinebackend.common.error.types.BadRequest;
import com.airline.airlinebackend.common.error.types.NotFound;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ErrorHandler {

   @ExceptionHandler(NotFound.class)
    public ResponseEntity<ErrorResponse> handleNotFoundError(NotFound e){
       ErrorResponse error=new ErrorResponse(HttpStatus.NOT_FOUND,e.getMessage(), LocalDateTime.now());
       return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
   }

   @ExceptionHandler(BadRequest.class)
   public ResponseEntity<ErrorResponse> handleBadRequest(BadRequest e){
       ErrorResponse error=new ErrorResponse(HttpStatus.BAD_REQUEST,e.getMessage(), LocalDateTime.now());
       return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
   }


   @ExceptionHandler(Exception.class)
   public ResponseEntity<ErrorResponse> handleGlobalError(Exception e){
       e.printStackTrace();
       ErrorResponse error=new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,e.getMessage(), LocalDateTime.now());
       return new ResponseEntity<>(error,HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
