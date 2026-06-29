package com.code.ecommerce.controller;

import com.code.ecommerce.common.constants.RequestTracker;
import com.code.ecommerce.dto.requests.orders.OrderRequest;
import com.code.ecommerce.service.orders.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderRequest request,
                                         @RequestHeader("orderId") String orderId) throws Exception {
        RequestTracker.addOrderKeys(orderId);  // checking for the order key
        orderService.createOrder(request);  // generating the order
        return null;
    }
}
