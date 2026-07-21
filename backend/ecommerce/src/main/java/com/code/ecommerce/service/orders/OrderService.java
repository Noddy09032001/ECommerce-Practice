package com.code.ecommerce.service.orders;

import com.code.ecommerce.dto.requests.orders.OrderRequest;
import com.code.ecommerce.dto.response.orders.OrderResponse;
import com.code.ecommerce.pojo.orders.Order;
import com.code.ecommerce.pojo.orders.OrderStatusHistory;
import jakarta.mail.MessagingException;

import java.time.LocalDateTime;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request) throws Exception;  // method to create the order
    void updateOrder() throws Exception;  // method to update the order
    Order fetchOrderByOrderId(String orderId) throws Exception;  // method to fetch the order by the order id
    OrderStatusHistory createOrderStatusHistory(Order order);   // method to generate the order status history
    void sendOrderConfirmationMail(String customerEmail, String customerName, String orderId, LocalDateTime orderDate) throws MessagingException;
}
