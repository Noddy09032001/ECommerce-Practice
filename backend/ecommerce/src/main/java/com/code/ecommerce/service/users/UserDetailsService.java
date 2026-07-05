package com.code.ecommerce.service.users;

import com.code.ecommerce.dto.requests.UserDetailsRequest;
import com.code.ecommerce.dto.response.UserDetailsResponse;
import com.code.ecommerce.pojo.UserDetails;

public interface UserDetailsService {

    UserDetailsResponse loginUser(UserDetailsRequest request) throws Exception;
    UserDetailsResponse signUp(UserDetailsRequest request) throws Exception;
    public UserDetails fetchUserByEmailAndPhoneNumber(String email, String phoneNumber);  // getting the user details by the email and phone number
}
