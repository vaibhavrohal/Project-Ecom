package com.ecomservice.ecomservice.controller.ControllerAdvice;

import com.ecomservice.ecomservice.Exception.InvalidTokenException;
import com.ecomservice.ecomservice.Exception.ProductNotFoundException;
import com.ecomservice.ecomservice.dto.ErrorResponsedto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerAdvice {
@ExceptionHandler (value= ProductNotFoundException.class)
    public ResponseEntity<ErrorResponsedto> handleProductNotFoundException(Exception e){
    ErrorResponsedto errorResponsedto=new ErrorResponsedto();
    errorResponsedto.setMessage(e.getMessage());
    errorResponsedto.setErrorcode(404);
    return new ResponseEntity<>(errorResponsedto, HttpStatus.NOT_FOUND);
}
    @ExceptionHandler(value = InvalidTokenException.class)
    public ResponseEntity<ErrorResponsedto> handleInvalidTokenException(Exception ex) {
        ErrorResponsedto errorResponse = new ErrorResponsedto();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setErrorcode(403);
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
