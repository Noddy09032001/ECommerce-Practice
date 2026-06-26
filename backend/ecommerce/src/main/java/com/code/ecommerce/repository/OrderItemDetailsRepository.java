package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.orders.OrderItemDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemDetailsRepository extends JpaRepository<OrderItemDetails, Long> {
}
