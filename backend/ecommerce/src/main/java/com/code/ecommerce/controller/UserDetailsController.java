package com.code.ecommerce.controller;

import com.code.ecommerce.common.constants.ApiMessageConstants;
import com.code.ecommerce.common.constants.RequestTracker;
import com.code.ecommerce.dto.requests.UserDetailsRequest;
import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.dto.response.UserDetailsResponse;
import com.code.ecommerce.exceptions.InvalidCredentialsException;
import com.code.ecommerce.exceptions.UserExistsException;
import com.code.ecommerce.service.users.UserDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserDetailsController {

    private UserDetailsService userDetailsService;

    @Autowired
    public UserDetailsController(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    /**
     * Authenticates a user using the provided credentials and return the user's details upon successful login.
     * @param request contains the user's login credentials
     * @return ResponseEntity containing an ApiResponse with the authenticated user's details and a success message
     * @throws InvalidCredentialsException if the provided credentials are invalid
     * @throws Exception if an unexpected error occurs during authentication
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserDetailsRequest request) throws Exception, InvalidCredentialsException {
        UserDetailsResponse response = userDetailsService.loginUser(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response);
        apiResponse.setStatusCode(HttpStatus.OK);
        apiResponse.setMessage(ApiMessageConstants.USER_LOGGED_IN.getMessage());
        return ResponseEntity.ok(apiResponse);
    }

    /**
     * Registers a new user account using the provided registration details.
     * @param request contains the user's registration information
     * @return ResponseEntity containing an ApiResponse with the newly registered user's details and a success message
     * @throws UserExistsException if a user with the provided email already exists
     * @throws Exception if an unexpected error occurs during registration
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDetailsRequest request,
                                          @RequestHeader("id") String id) throws Exception, UserExistsException {
        RequestTracker.addRequestKey(id);  // check if the same request is being sent again
        UserDetailsResponse response = userDetailsService.signUp(request);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response);
        apiResponse.setStatusCode(HttpStatus.CREATED);
        apiResponse.setMessage(ApiMessageConstants.USER_CREATED.getMessage());

        return ResponseEntity.ok(apiResponse);
    }
}
