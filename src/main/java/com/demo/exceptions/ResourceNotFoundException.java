package com.demo.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String message;

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
