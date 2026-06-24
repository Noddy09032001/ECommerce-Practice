package com.code.ecommerce.common.constants;

// tracking the statuses of the orders generated
public enum OrderStatus {
    ORDER_CREATED,  // order has been created
    ORDER_PLACED,  // order has been placed
    ORDER_DISPATCHED,  // order has been dispatched
    ORDER_RECEIVED,  // order has been received by the user
    ORDER_CANCELLED,  // order has been canceled by the user
}
