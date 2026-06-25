package com.code.ecommerce.service;

import com.code.ecommerce.dto.requests.UserDetailsRequest;
import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.dto.response.UserDetailsResponse;
import com.code.ecommerce.exceptions.InvalidCredentialsException;
import com.code.ecommerce.exceptions.UserExistsException;
import com.code.ecommerce.pojo.UserDetails;
import com.code.ecommerce.repository.UserDetailsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{

    private final UserDetailsRepository userDetailsRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImplementation.class);
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);

    @Autowired
    public UserDetailsServiceImplementation(UserDetailsRepository userDetailsRepository){
        this.userDetailsRepository = userDetailsRepository;
    }

    /**
     * method to log in the user based on the provided credentials
     * @param request the request body containing the user credentials
     * @return the details of the user based on the credentials
     * @throws Exception invalid credentials exception
     */
    @Override
    public UserDetailsResponse loginUser(UserDetailsRequest request) throws Exception {
        logger.info("Inside the login user method - ");
        try{
            UserDetails currentUser = userDetailsRepository.findUserDetailsByEmail(request.getEmail());
            if(currentUser == null)
                throw new InvalidCredentialsException("Invalid username");

            String password = currentUser.getPassword();  // getting the password stored in the database
            boolean isPasswordMatch = encoder.matches(request.getPassword(), password);
            if(!isPasswordMatch)
                throw new InvalidCredentialsException("Passwords do not match");

            return generateResponse(currentUser);  //generating the response to be returned
        }
        catch (Exception e){
            logger.info("Error logging in - {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Registers a new user in the system.
     * @param request the registration request containing user information
     * @return details of the successfully registered user
     * @throws UserExistsException if an account with the given email already exists
     */
    @Override
    public UserDetailsResponse signUp(UserDetailsRequest request) throws Exception {
        logger.info("Inside the signup user method - ");
        try{
            // handles the user with the duplicates
            UserDetails currentUser = userDetailsRepository.findUserDetailsByEmail(request.getEmail());
            if(currentUser != null)
                throw new UserExistsException("User with the current email exists");

            String encodedPassword = encoder.encode(request.getPassword());

            UserDetails newUser = new UserDetails(request.getName(), encodedPassword, request.getEmail(),
                    request.getPhoneNumber(), request.getCity(), request.getState(), request.getCountry(),
                    request.getPinCode(), request.getAddress(), request.getGender(), request.getRole()
            );

            UserDetails savedUser = userDetailsRepository.save(newUser);  // saving and retrieving the current user
            return generateResponse(savedUser); // returning the response for the same
        }
        catch (Exception e){
            logger.info("Error creating the user - {}", e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Converts a UserDetails entity into a UserDetailsResponse DTO.
     * @param user the user entity to be converted
     * @return the corresponding response DTO containing user information
     */
    public UserDetailsResponse generateResponse(UserDetails user){
        UserDetailsResponse response = new UserDetailsResponse();
        response.setName(user.getName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().toString());
        response.setGender(user.getGender().toString());
        response.setCity(user.getCity());
        response.setCountry(user.getCountry());
        response.setState(user.getState());

        return response;  // returning the response
    }
}
