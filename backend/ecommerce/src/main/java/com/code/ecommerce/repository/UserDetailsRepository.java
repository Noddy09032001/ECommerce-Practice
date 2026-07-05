package com.code.ecommerce.repository;

import com.code.ecommerce.pojo.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {
    UserDetails findUserDetailsByEmail(String email);  // getting the user details by email
    // method to fetch the user by the username and password
    // method to fetch the user by the email and password

    @Query("SELECT u FROM UserDetails u WHERE u.email=:email AND u.phoneNumber=:phone")
    UserDetails findUserDetailsByEmailAndPhoneNumber(@Param("email") String email, @Param("phone") String phoneNumber);
}
