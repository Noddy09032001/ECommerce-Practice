package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.orders.OrderPaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderPaymentDetailsRepository extends JpaRepository<OrderPaymentDetails, Long> {
}
