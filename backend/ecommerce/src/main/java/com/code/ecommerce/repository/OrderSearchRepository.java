package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.orderSearch.OrderSearchRequest;
import com.code.ecommerce.dto.response.orderSearch.OrderSearchResponse;
import org.springframework.data.domain.Page;

public interface OrderSearchRepository {
    Page<OrderSearchResponse> searchOrder(OrderSearchRequest request);  // getting the order details based on the request parameters
}
