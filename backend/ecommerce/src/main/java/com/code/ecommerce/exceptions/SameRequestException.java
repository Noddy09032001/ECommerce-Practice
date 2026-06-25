package com.code.ecommerce.exceptions;

// exception class for the same request being sent again
public class SameRequestException extends RuntimeException{
    public SameRequestException(String message){
        super(message);
    }
}
