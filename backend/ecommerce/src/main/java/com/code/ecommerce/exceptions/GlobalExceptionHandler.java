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

    /**
     * Handles duplication of requests being sent due to multiple clicks
     * @param sameRequestException the custom exception containing the same request handling message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(SameRequestException.class)
    public ResponseEntity<ApiResponse> handleSameRequestException(SameRequestException sameRequestException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(sameRequestException.getMessage());
        response.setStatusCode(HttpStatus.ALREADY_REPORTED);
        return new ResponseEntity<>(response, HttpStatus.ALREADY_REPORTED);   // returning the response body
    }

    /**
     * handles the condition of invalid item fetching due to incorrect id's
     * @param invalidItemException the custom exception containing invalid item error message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(InvalidItemException.class)
    public ResponseEntity<ApiResponse> handleInvalidItemException(InvalidItemException invalidItemException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(invalidItemException.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);   // returning the response body
    }

    /**
     * handles the condition of invalid item fetching due to incorrect id's
     * @param invalidSellerException the custom exception containing invalid seller error message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(InvalidSellerException.class)
    public ResponseEntity<ApiResponse> handleInvalidSellerException(InvalidSellerException invalidSellerException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(invalidSellerException.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);   // returning the response body
    }

    /**
     * handles the condition of invalid item fetching due to incorrect id's
     * @param invalidSellerItemException the custom exception containing invalid seller item error message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(InvalidSellerItemException.class)
    public ResponseEntity<ApiResponse> handleInvalidSellerItemException(InvalidSellerItemException invalidSellerItemException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(invalidSellerItemException.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);   // returning the response body
    }

    /**
     * handles the condition of invalid global item search due to incorrect parameters
     * @param itemSearchException the custom exception containing invalid item search error message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(InvalidSellerItemException.class)
    public ResponseEntity<ApiResponse> handleItemSearchException(ItemSearchException itemSearchException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(itemSearchException.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);   // returning the response body
    }

    /**
     * handles the condition of invalid global order search due to incorrect parameters
     * @param orderSearchException the custom exception containing invalid order search error message
     * @return ResponseEntity containing a standardized ApiResponse with an appropriate HTTP error status
     */
    @ExceptionHandler(InvalidSellerItemException.class)
    public ResponseEntity<ApiResponse> handleOrderSearchException(OrderSearchException orderSearchException){
        ApiResponse response = new ApiResponse();
        response.setData(null);
        response.setMessage(orderSearchException.getMessage());
        response.setStatusCode(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);   // returning the response body
    }
}
