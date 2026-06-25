package com.code.ecommerce.common.constants;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// will go in the custom API response body
public enum ApiMessageConstants {
    ORDER_CREATED("Order has been created successfully"),  // order has been created
    ORDER_PLACED("Order has been placed successfully"),  // order has been placed
    ORDER_DISPATCHED("Order has been dispatched successfully"),  // order has been dispatched
    ORDER_RECEIVED("Order has been received by the user"),  // order has been received by the user
    ORDER_CANCELLED("Order has been cancelled by the user"),  // order has been canceled by the user
    USER_CREATED("User created successfully"),  // user has been created
    USER_LOGGED_IN("User logged in successfully");  // user has been logged in successfully

    private final String message;   // getting the message from the constant

    public String getMessage() {
        return message;
    }

    ApiMessageConstants(String message){
        this.message = message;
    }
}
