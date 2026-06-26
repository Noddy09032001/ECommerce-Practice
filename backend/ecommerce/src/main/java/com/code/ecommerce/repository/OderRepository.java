package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OderRepository extends JpaRepository<Order, Long> {
}
