package com.demo.exceptions;

public class BadRequestException extends RuntimeException{
    String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
