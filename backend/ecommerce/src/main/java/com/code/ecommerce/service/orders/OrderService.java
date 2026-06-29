package com.code.ecommerce.service.orders;

import com.code.ecommerce.dto.requests.orders.OrderRequest;

public interface OrderService {

    void createOrder(OrderRequest request) throws Exception;  // method to create the order
    void updateOrder() throws Exception;  // method to update the order
    void fetchOrderByOrderId(String orderId) throws Exception;  // method to fetch the order by the order id
}
