package com.code.ecommerce.service;

import com.code.ecommerce.dto.requests.UserDetailsRequest;
import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.dto.response.UserDetailsResponse;

public interface UserDetailsService {

    UserDetailsResponse loginUser(UserDetailsRequest request) throws Exception;
    UserDetailsResponse signUp(UserDetailsRequest request) throws Exception;
}
