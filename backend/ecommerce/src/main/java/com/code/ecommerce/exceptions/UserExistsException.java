package com.code.ecommerce.exceptions;

// exception class for user not existing
public class UserExistsException extends RuntimeException{
    public UserExistsException(String message){
        super(message);  // passing it to the runtime exception class
    }
}
