package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    // method to fetch the user by the username and password

    // method to fetch the user by the email and password 
}
