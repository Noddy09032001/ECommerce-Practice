package com.code.ecommerce.service.items;

import com.code.ecommerce.dto.requests.ItemRequest;
import com.code.ecommerce.dto.requests.SellerItemRequest;
import com.code.ecommerce.dto.response.ItemResponse;
import com.code.ecommerce.dto.response.SellerItemResponse;
import com.code.ecommerce.exceptions.InvalidItemException;
import com.code.ecommerce.exceptions.InvalidSellerException;
import com.code.ecommerce.pojo.Item;
import com.code.ecommerce.pojo.Seller;
import com.code.ecommerce.pojo.SellerItemMapping;
import com.code.ecommerce.repository.ItemRepository;
import com.code.ecommerce.repository.SellerItemMappingRepository;
import com.code.ecommerce.repository.SellerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ItemServiceImplementation implements ItemService {

    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImplementation.class);
    private final ItemRepository itemRepository;
    private final SellerRepository sellerRepository;
    private final SellerItemMappingRepository sellerItemMappingRepository;

    @Autowired
    public ItemServiceImplementation(ItemRepository itemRepository, SellerRepository sellerRepository,
                                     SellerItemMappingRepository sellerItemMappingRepository){
        this.itemRepository = itemRepository;
        this.sellerRepository = sellerRepository;
        this.sellerItemMappingRepository = sellerItemMappingRepository;
    }


    /**
     * Creates a new item from the specified request.
     *
     * @param request the item creation request
     * @return the created item response
     * @throws Exception if the item creation process fails
     */
    @Override
    public ItemResponse createItem(ItemRequest request) throws Exception {
        logger.info("Inside the create item method - ");
        try{
            Item item = new Item();
            item.setItemName(request.getName());  // setting the item name
            item.setItemDescription(request.getDescription());  // setting the description
            item.setCategory(request.getCategory());  // setting the item category
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
            List<SellerItemResponse> merchants = this.generateSellerItemMappings(request.getSellers(), currentItem);  // getting the merchant data

            ItemResponse response = generateResponse(currentItem);  // generating response
            response.setMerchants(merchants);  // setting the merchant data

            return response;

        } catch (Exception e) {
            logger.info("Error creating item - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemResponse updateItem(ItemRequest request) throws Exception {
        logger.info("Inside the update item method - ");
        try{
            return null;
        } catch (Exception e) {
            logger.info("Error updating item - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public ItemResponse getItemById(Long id) throws Exception {
        logger.info("Inside the fetch item by id method - ");
        try{
            Item item = itemRepository.findById(id).orElseThrow(() ->
                    new InvalidItemException("Invalid Item id. Item does not exist"));

            List<SellerItemMapping> mapping = item.getSellers();  // getting the associated merchants for the item

            ItemResponse response = this.generateResponse(item);  // getting the item response
            response.setMerchants(this.generateSellerItemResponse(mapping));  // setting the info of the merchants associated

            return response;  // returning the response
        } catch (Exception e) {
            logger.info("Error fetching item by id - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ItemResponse> getAllItems() throws Exception {
        logger.info("Inside the get all items method - ");
        try{
            List<Item> items = itemRepository.findAll();  // getting the list of all the items
            List<ItemResponse> responses = new ArrayList<>();  // list to add all the items in the needed response format
            for(Item item : items){
                ItemResponse response = this.generateResponse(item);  // generating response for the item
                List<SellerItemMapping> mappings = item.getSellers();  // getting the merchants associated with the item

                response.setMerchants(this.generateSellerItemResponse(mappings));  // setting the merchant details
                responses.add(response);  // adding to the list of responses
            }

            return responses;  // returning the list of items in the response format
        } catch (Exception e) {
            logger.info("Error getting the list of all items - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * method to generate the sku id for the given item
     *
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
     * Generates seller-item mappings for the specified item.
     *
     * @param sellerItemRequest the list of seller item requests
     * @param item the item entity to be associated with the sellers
     * @return responses for the mappings generated between merchant and the items
     */
    public List<SellerItemResponse> generateSellerItemMappings(List<SellerItemRequest> sellerItemRequest,
                                           Item item){
        List<SellerItemMapping> itemMappingList = new ArrayList<>();

        for (SellerItemRequest sellerItemRequest1 : sellerItemRequest){
            SellerItemMapping mapping = new SellerItemMapping();
            mapping.setItem(item);  // setting the item
            mapping.setOtherCharges(sellerItemRequest1.getOtherCharges());  // setting other charges for the item
            mapping.setTransportationCharges(sellerItemRequest1.getTransportationCharges());  // setting the transportation charges
            mapping.setCreatedOn(LocalDateTime.now());  // setting the created time
            mapping.setModifiedOn(LocalDateTime.now());  // setting the modified time

            Seller currentSeller = this.getSellerById(sellerItemRequest1.getSellerId());
            mapping.setSeller(currentSeller);  // setting the seller for the item

            mapping.setAmount(sellerItemRequest1.getAmount());  // setting the initial amount
            mapping.setActive(true); // setting the mapping to active

            // getting the final amount with the tax calculations
            Double totalAmountWithTax = this.calculateAmountWithTax(sellerItemRequest1.getAmount(),
                    item.getCgst(), item.getSgst(), item.getIgst(), item.getVat(), item.getCess());

            mapping.setTotalCost(totalAmountWithTax);  // setting the final amount
            SellerItemMapping currentMap = sellerItemMappingRepository.save(mapping);  // saving the seller item mapping

            itemMappingList.add(currentMap);  // adding to the item mapping list
        }

        return this.generateSellerItemResponse(itemMappingList);  // return the response
    }

    /**
     * method - return the merchant / seller data model by id
     *
     * @param id the id of the merchant / seller to be found
     * @return the associated merchant details with the id
     */
    public Seller getSellerById(Long id){
        return sellerRepository.findById(id).
                orElseThrow(() -> new InvalidSellerException("Invalid merchant id. Merchant does not exist"));  // returning the seller details
    }

    /**
     * Calculates the total amount including applicable taxes.
     *
     * @param amount the amount before taxes
     * @param cgst the CGST percentage
     * @param sgst the SGST percentage
     * @param igst the IGST percentage
     * @param vat the VAT percentage
     * @param cess the cess percentage
     * @return the amount after applying all provided taxes
     */
    public Double calculateAmountWithTax(Double amount, Float cgst, Float sgst,
                                         Float igst, Float vat, Float cess) {

        // getting the amounts for the individual tax break down components
        Double cgstAmount = amount * (cgst) / 100.0;
        Double sgstAmount = amount * (sgst) / 100.0;
        Double igstAmount = amount * (igst) / 100.0;
        Double vatAmount  = amount * (vat) / 100.0;
        Double cessAmount = amount * (cess) / 100.0;

        return amount + cgstAmount + sgstAmount + igstAmount + vatAmount + cessAmount;  // returning the combined amount
    }

    /**
     * Generates item response from the current item
     *
     * @param item the item entity
     * @return the corresponding item response
     */
    public ItemResponse generateResponse(Item item){
        ItemResponse response = new ItemResponse();
        response.setName(item.getItemName());  // setting the item name
        response.setCategory(item.getCategory());  // setting the category
        response.setDescription(item.getItemDescription());  // setting the description
        response.setSku(item.getSku());  // setting the sku

        // setting the auditing information in the response
        response.setCreatedOn(item.getCreatedOn());
        response.setModifiedOn(item.getModifiedOn());

        // setting the taxes information in the response
        response.setCgst(item.getCgst());
        response.setSgst(item.getSgst());
        response.setIgst(item.getIgst());
        response.setVat(item.getVat());
        response.setCess(item.getCess());
        return response;  // returning the item response
    }

    /**
     * Generates a seller item response by combining the seller details and the corresponding seller-item mapping information.
     *
     * @param sellerItemList the list of all the seller item mappings to be converted to response format
     * @return the generated seller item response
     */
    public List<SellerItemResponse> generateSellerItemResponse(List<SellerItemMapping> sellerItemList){
        List<SellerItemResponse> responses = new ArrayList<>();
        for(SellerItemMapping mapping : sellerItemList){
            SellerItemResponse response = new SellerItemResponse();
            response.setAmount(mapping.getAmount());  // setting the amount without tax
            response.setName(mapping.getSeller().getSellerName());   // setting the name of the merchant
            response.setOtherCharges(mapping.getOtherCharges());  // setting the other charges
            response.setTransportationCharges(mapping.getTransportationCharges());  // setting the transportation charges
            response.setFinalAmount(mapping.getTotalCost());  // setting the cost with tax
            response.setQuantity(mapping.getAvailableQuantity());  // setting the quantity
            responses.add(response);   // adding the response to the list
        }
        return responses;  // returning the responses list
    }
}
