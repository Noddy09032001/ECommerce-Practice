package com.code.ecommerce.service.orders;

import com.code.ecommerce.pojo.orders.OrderItemDetails;

import java.util.List;

public interface PaymentService {

    String initiatePayment(List<OrderItemDetails> items) throws Exception;
}
