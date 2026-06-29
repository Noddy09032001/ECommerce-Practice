package com.code.ecommerce.service;

import com.code.ecommerce.dto.requests.ItemRequest;
import com.code.ecommerce.dto.response.ItemResponse;

import java.util.List;

public interface ItemService {

    ItemResponse createItem(ItemRequest request) throws Exception;  // creating a new item
    ItemResponse updateItem(ItemRequest request) throws Exception;  // updating an existing item
    ItemResponse getItemById(Long id) throws Exception;  // fetching an item by the id
    List<ItemResponse> getAllItems() throws Exception;  // getting the list of all the available items
}
