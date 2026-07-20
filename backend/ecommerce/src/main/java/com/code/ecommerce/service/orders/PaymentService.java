package com.code.ecommerce.service.orders;

import com.code.ecommerce.dto.requests.payments.PaymentItem;
import com.code.ecommerce.dto.requests.payments.PaymentRequest;
import com.code.ecommerce.pojo.orders.OrderItemDetails;

import java.util.List;

public interface PaymentService {
    String initiatePayment(PaymentRequest request) throws Exception;  // method to generate the payment url based on the items in the order
}
