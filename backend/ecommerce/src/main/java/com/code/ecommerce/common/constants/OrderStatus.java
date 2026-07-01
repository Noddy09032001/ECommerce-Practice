package com.code.ecommerce.common.constants;

// tracking the statuses of the orders generated
public enum OrderStatus {
    ORDER_CREATED("Order has been created successfully"),  // order has been created
    ORDER_PLACED("Order has been placed successfully"),  // order has been placed
    ORDER_DISPATCHED("Order has been dispatched successfully"),  // order has been dispatched
    ORDER_RECEIVED("Order has been received by the user"),  // order has been received by the user
    ORDER_CANCELLED("Order has been cancelled by the user");  // order has been canceled by the user

    private final String message;   // getting the message from the constant

    OrderStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
