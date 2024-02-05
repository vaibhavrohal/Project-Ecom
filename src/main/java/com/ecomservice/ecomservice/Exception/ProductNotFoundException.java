package com.ecomservice.ecomservice.Exception;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }

    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
