package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT MAX(i.id) FROM Item i")
    Long getLatestId();   // getting the latest id for the item
}
