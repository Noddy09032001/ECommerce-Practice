package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.orders.OrderStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderStatusRepository extends JpaRepository<OrderStatusHistory, Long> {
}
