package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.SellerItemMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerItemMappingRepository extends JpaRepository<SellerItemMapping, Long> {
}
