package com.code.ecommerce.controller;

import com.code.ecommerce.dto.response.ApiResponse;
import com.code.ecommerce.dto.response.ItemResponse;
import com.code.ecommerce.service.items.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * fetch all the current items from the database
     * @return the list of all the current items
     * @throws Exception if any error during fetching of the items
     */
    @GetMapping("/allItems")
    public ResponseEntity<?> fetchAllItems() throws Exception {
        List<ItemResponse> response = itemService.getAllItems();
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatusCode(HttpStatus.OK);  // setting the status code for the response
        apiResponse.setData(response);  // setting the data for the response
        apiResponse.setMessage("Data for all items fetched successfully");   // setting the message for the response
        return ResponseEntity.ok(apiResponse);  // returning the response in the entity format
    }
}
