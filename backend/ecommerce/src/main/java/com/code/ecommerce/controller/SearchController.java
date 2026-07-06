package com.code.ecommerce.controller;

import com.code.ecommerce.dto.requests.ItemSearchRequest;
import com.code.ecommerce.dto.requests.orders.OrderSearchRequest;
import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.dto.response.ItemCatalogueResponse;
import com.code.ecommerce.service.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    /**
     * Searches the item catalogue.
     *
     * @param request search request containing the search parameters
     * @return paginated catalogue response for the given search parameters
     */
    @PostMapping("/items-search")
    public ResponseEntity<?> searchItems(@RequestBody ItemSearchRequest request){
        Page<ItemCatalogueResponse> response = searchService.searchItems(request);  // getting the paginated response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response);  // setting the response in the data of the api response body
        apiResponse.setMessage("Data for the search filters for the item found successfully");  // setting the message
        apiResponse.setStatusCode(HttpStatus.OK);  // setting the status code
        return ResponseEntity.ok(apiResponse);  // setting and returning the response entity
    }

    /**
     * Searches for the orders for the given user
     *
     * @param request search request containing the search parameters for the order
     * @return paginated catalogue response for the given search parameters
     */
    @PostMapping("/orders-search")
    public ResponseEntity<?> searchOrders(@RequestBody OrderSearchRequest request){
        Page<?> response = searchService.searchOrder(request);  // getting the paginated response
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(response);  // setting the response in the data of the api response body
        apiResponse.setMessage("Data for the search filters for the orders found successfully");  // setting the message
        apiResponse.setStatusCode(HttpStatus.OK);  // setting the status code
        return ResponseEntity.ok(apiResponse);  // setting and returning the response entity
    }
}
