package com.code.ecommerce.service;

import com.code.ecommerce.dto.requests.ItemRequest;

public interface ItemService {

    void createItem(ItemRequest request) throws Exception;  // creating a new item
    void updateItem(ItemRequest request) throws Exception;  // updating an existing item
    void getItemById(Long id) throws Exception;  // fetching an item by the id
    void getAllItems() throws Exception;  // getting the list of all the available items
}
