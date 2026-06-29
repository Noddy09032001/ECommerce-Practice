package com.code.ecommerce.service;

import com.code.ecommerce.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImplementation implements ItemService{

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }


    @Override
    public void createItem() throws Exception {
        logger.info("Inside the create item method - ");
        try{

        } catch (Exception e) {
            logger.info("Error creating item - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem() throws Exception {
        logger.info("Inside the update item method - ");
        try{

        } catch (Exception e) {
            logger.info("Error updating item - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getItemById(Long id) throws Exception {
        logger.info("Inside the fetch item by id method - ");
        try{

        } catch (Exception e) {
            logger.info("Error fetching item by id - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void getAllItems() throws Exception {
        logger.info("Inside the get all items method - ");
        try{

        } catch (Exception e) {
            logger.info("Error getting the list of all items - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
