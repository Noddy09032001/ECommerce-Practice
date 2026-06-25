package com.code.ecommerce.exceptions;
import com.code.ecommerce.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// generic and global exception handler code for handling all the exceptions
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles the exception when a user tries to register with an email that already exists in the system.
     * @param userExistsException the custom exception containing the error details
     * @return ResponseEntity containing a standardized ApiResponse with HTTP 409 (CONFLICT) status
     */
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ApiResponse> handleUserExistException(UserExistsException userExistsException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(userExistsException.getMessage());
        response.setStatusCode(HttpStatus.CONFLICT);
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);   // returning the response body
    }

    /**
     * Handles authentication failures caused by invalid login credentials.
     * @param invalidCredentialsException the custom exception containing the authentication failure message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ApiResponse> handleInvalidCredentialsException(InvalidCredentialsException invalidCredentialsException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(invalidCredentialsException.getMessage());
        response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
