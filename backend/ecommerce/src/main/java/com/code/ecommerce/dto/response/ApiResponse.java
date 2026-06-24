package com.code.ecommerce.dto.response;

import com.code.ecommerce.common.constants.ApiMessageConstants;
import org.springframework.http.HttpStatus;

// custom class for generating the api response body
public class ApiResponse {

    private Object data;
    private HttpStatus statusCode;
    private String message;

    public ApiResponse(){}

    public ApiResponse(Object data, HttpStatus statusCode, String message) {
        this.data = data;
        this.statusCode = statusCode;
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
