package com.code.ecommerce.controller;

import com.code.ecommerce.common.constants.RequestTracker;
import com.code.ecommerce.dto.requests.orders.OrderRequest;
import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.dto.response.orders.OrderResponse;
import com.code.ecommerce.service.orders.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    /**
     * Create a new order in the database and generate and return the order response
     *
     * @param request the request body containing the order details
     * @param idempotencyKey the key for checking concurrent double order hits and maintaining idempotency
     * @return the order response body
     * @throws Exception if any errors occur during order creation
     */
    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request,
                                         @RequestHeader("idempotency-key") String idempotencyKey) throws Exception {
        if(!RequestTracker.addOrderKeys(idempotencyKey)) // checking for the order key
            throw new RuntimeException("Request with the same identifier key identified. Duplicate request");
        OrderResponse orderResponse = orderService.createOrder(request);  // generating the order
        ApiResponse response = new ApiResponse();
        response.setMessage("Order has been created successfully");
        response.setData(orderResponse);
        response.setStatusCode(HttpStatus.CREATED);
        return ResponseEntity.ok(response);
    }
}
