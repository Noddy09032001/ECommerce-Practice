package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, OrderSearchRepository {

    @Query("SELECT o FROM Order o WHERE o.orderId = :orderId")
    Order findByOrderId(@Param("orderId") String orderId);   // find the order by the order id
}
