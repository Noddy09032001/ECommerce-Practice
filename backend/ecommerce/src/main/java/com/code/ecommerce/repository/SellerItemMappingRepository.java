package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.SellerItemMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SellerItemMappingRepository extends JpaRepository<SellerItemMapping, Long> {

    @Query("SELECT sim FROM SellerItemMapping sim WHERE sim.item.id = :itemId AND sim.seller.id = :sellerId")
    SellerItemMapping findByItemAndSeller(@Param("itemId") Long itemId,
            @Param("sellerId") Long sellerId); // getting the item by the item id and the seller id
}
