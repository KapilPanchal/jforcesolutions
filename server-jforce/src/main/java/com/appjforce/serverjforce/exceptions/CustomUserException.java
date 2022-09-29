package com.appjforce.serverjforce.exceptions;

public class CustomUserException extends RuntimeException{
    public CustomUserException(String message) {
        super(message);
    }

    public CustomUserException(String message, Throwable cause) {
        super(message, cause);
    }
}