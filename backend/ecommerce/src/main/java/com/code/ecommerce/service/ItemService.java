package com.code.ecommerce.service;

public interface ItemService {

    void createItem() throws Exception;  // creating a new item
    void updateItem() throws Exception;  // updating an existing item
    void getItemById(Long id) throws Exception;  // fetching an item by the id
    void getAllItems() throws Exception;  // getting the list of all the available items
}
