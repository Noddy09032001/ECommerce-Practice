package com.code.ecommerce.exceptions;

// exception class for the invalid credentials found
public class InvalidCredentialsException extends RuntimeException{
    public InvalidCredentialsException(String message){
        super(message);
    }
}
