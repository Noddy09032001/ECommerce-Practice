package com.code.ecommerce.service;

import com.code.ecommerce.dto.requests.ItemRequest;
import com.code.ecommerce.dto.requests.SellerItemRequest;
import com.code.ecommerce.pojo.Item;
import com.code.ecommerce.pojo.SellerItemMapping;
import com.code.ecommerce.repository.ItemRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImplementation implements ItemService{

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);
    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }


    @Override
    public void createItem(ItemRequest request) throws Exception {
        logger.info("Inside the create item method - ");
        try{
            Item item = new Item();
            item.setItemName(request.getName());  // setting the item name
            item.setItemDescription(request.getDescription());  // setting the description
            item.setCreatedOn(LocalDateTime.now());  // setting the created date
            item.setModifiedOn(LocalDateTime.now());  // setting the modified date

            // setting the taxes and the charges for the item
            item.setCgst(request.getCgst());
            item.setSgst(request.getSgst());
            item.setIgst(request.getIgst());
            item.setVat(request.getVat());
            item.setCess(request.getCess());

            String sku = this.generateSkuForItem(request.getCategory());  // getting the sku based on the item category
            item.setSku(sku);  // setting the sku for the item

            Item currentItem = itemRepository.save(item);  // saving and retrieving the current item
            calculateAmountWithTaxForAllSellersForItem(request.getSellers(), request);

        } catch (Exception e) {
            logger.info("Error creating item - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateItem(ItemRequest request) throws Exception {
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

    /**
     * method to generate the sku id for the given item
     * @param category the category which the item belongs to
     * @return the generated sku for the item
     */
    public String generateSkuForItem(String category){
        String prefix = category.toUpperCase().replaceAll("[^A-Z]", "");

        prefix = prefix.length() >= 3 ? prefix.substring(0, 3)
                : String.format("%-3s", prefix).replace(' ', 'X');

        return prefix + "-"
                + UUID.randomUUID()
                .toString().replace("-", "").substring(0, 8).toUpperCase();
    }

    /**
     * Calculates the total amount by adding all applicable taxes to the base amount.
     * @param request the item request containing amount and tax percentages
     * @return the final amount after tax calculation
     */
    public void calculateAmountWithTaxForAllSellersForItem(List<SellerItemRequest> request, ItemRequest item){
        for (SellerItemRequest itemRequest : request){
            SellerItemMapping mapping = new SellerItemMapping();
            mapping.set
        }

    }
}
