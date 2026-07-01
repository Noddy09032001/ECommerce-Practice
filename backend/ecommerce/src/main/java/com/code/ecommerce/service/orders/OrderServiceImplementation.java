package com.code.ecommerce.service.orders;

import com.code.ecommerce.common.constants.OrderStatus;
import com.code.ecommerce.dto.requests.orders.OrderItemRequest;
import com.code.ecommerce.dto.requests.orders.OrderRequest;
import com.code.ecommerce.exceptions.InvalidItemException;
import com.code.ecommerce.exceptions.InvalidSellerItemException;
import com.code.ecommerce.pojo.Item;
import com.code.ecommerce.pojo.SellerItemMapping;
import com.code.ecommerce.pojo.orders.Order;
import com.code.ecommerce.pojo.orders.OrderDetails;
import com.code.ecommerce.pojo.orders.OrderItemDetails;
import com.code.ecommerce.pojo.orders.OrderStatusHistory;
import com.code.ecommerce.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderServiceImplementation implements OrderService {

    private static Logger logger = LoggerFactory.getLogger(OrderServiceImplementation.class);

    private final OrderRepository orderRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderItemDetailsRepository orderItemDetailsRepository;
    private final ItemRepository itemRepository;
    private final SellerItemMappingRepository sellerItemMappingRepository;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                                      OrderItemDetailsRepository orderItemDetailsRepository, ItemRepository itemRepository,
                                      SellerItemMappingRepository sellerItemMappingRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderItemDetailsRepository = orderItemDetailsRepository;
        this.itemRepository = itemRepository;
        this.sellerItemMappingRepository = sellerItemMappingRepository;
    }

    /**
     * method to generate unique order id for the given order
     * @return the generated order id
     */
    public String generateOrderId(){
        return "ORD-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }

    /**
     * method to generate the unique order details id for the given order
     * @return the generated unique order details id
     */
    public String generateOrderDetailsId(){
        return "ORD-DET-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }

    @Override
    public void createOrder(OrderRequest request) throws Exception {
        logger.info("Inside the create order method - ");
        try{
            String orderId = generateOrderId();  // getting the order id
            String orderDetailsId = this.generateOrderDetailsId();  // generating the order details id

            Order order = generateOrder(orderId);  // getting the order object
            OrderDetails orderDetails = this.generateOrderDetails(orderDetailsId);  // getting the order details object

            order.setTotalItems(request.getItems().size());  // setting the total items in the order
            order.setCurrentStatus(OrderStatus.ORDER_CREATED.toString());  // setting the current status of the order created

            Order currentOrder = orderRepository.save(order);  // saving and returning the current order id
            orderDetails.setOrder(currentOrder);  // saving the current order inside the order details

            // saving the shipping address of the order
            orderDetails.setCity(request.getAddress().getCity());
            orderDetails.setState(request.getAddress().getState());
            orderDetails.setAddress(request.getAddress().getStreet());

            // setting and generating the order items
            List<OrderItemDetails> items = this.generateOrderItems(request.getItems(), currentOrder);
            Double grandOrderTotal = this.getOrderGrandTotal(items);  // getting the grand total of the order


            Map<String, Double> amounts = this.getOrderDetailsAmounts(items);  // getting the map of the amounts
            orderDetails.setTotalTaxInAmount(amounts.get("tax_amount"));
            orderDetails.setTotalOrderAmount(amounts.get("total_amount"));
            orderDetails.setAmount(amounts.get("amount_without_tax"));
            orderDetailsRepository.save(orderDetails);  // saving the order details data object

            currentOrder.setOrderItems(items);  // setting the order items for the current order
            currentOrder.setGrandTotal(grandOrderTotal);  // setting the total amount of the current order

            orderItemDetailsRepository.saveAll(items);  // saving the list of items in the current order

            orderRepository.save(currentOrder);  // saving the updated order model

            OrderStatusHistory orderStatus = this.createOrderStatusHistory(currentOrder);  // getting the current status of the order

        } catch (Exception e) {
            logger.info("Error creating order: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateOrder() throws Exception {
        logger.info("Inside the update order method - ");
        try{

        } catch (Exception e) {
            logger.info("Error updating order: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public void fetchOrderByOrderId(String orderId) throws Exception {
        logger.info("Inside the fetch order by order id method - ");
        try{

        } catch (Exception e) {
            logger.info("Error fetching order: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * method - save and generate the order
     * @param orderId the unique id of the order
     * @return the order object being generated
     */
    public Order generateOrder(String orderId){
        Order order = new Order();
        order.setOrderId(orderId);  // setting the order id being generated
        order.setCreatedOn(LocalDateTime.now());  // setting the created on
        order.setModifiedOn(LocalDateTime.now());  // setting the modified on
        return order;  // return the generated order object
    }

    /**
     * method - save and generate the order item details
     * @param orderDetailsId the unique id of the order details of the order
     * @return the order details object generated
     */
    public OrderDetails generateOrderDetails(String orderDetailsId){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderDetailsId(orderDetailsId);  // setting the order details id
        orderDetails.setCreatedOn(LocalDateTime.now());  // setting the created on date
        orderDetails.setModifiedOn(LocalDateTime.now());  // setting the modified on date
        return orderDetails;  // returning the order details object
    }

    /**
     * method - to fetch and generate order items list
     * @param items the items present in the request body
     * @param order the order object associated with the current items under consideration
     * @return the list of order items fetched from the data
     */
    public List<OrderItemDetails> generateOrderItems(List<OrderItemRequest> items, Order order){
        List<OrderItemDetails> details = new ArrayList<>();
        for (OrderItemRequest request : items){
            Item itemDetails = itemRepository.findById(request.getId())
                    .orElseThrow(() -> new InvalidItemException("Invalid Item Id. Item not found"));
            OrderItemDetails orderItemDetails = this.generateOrderItemDetailsObject(itemDetails, request);
            orderItemDetails.setOrder(order);  // setting the order for mapping
            details.add(orderItemDetails);  // adding to the list
        }
        return details;  // return the list of items
    }

    /**
     * method - to generate the order item details data model
     * @param details the item details object to be converted
     * @param request the request body of the current item to be added to the order
     * @return the order item details data object model
     */
    public OrderItemDetails generateOrderItemDetailsObject(Item details, OrderItemRequest request){
        SellerItemMapping mapping = sellerItemMappingRepository.findByItemAndSeller(details.getId(), request.getSellerId());
        if(mapping == null)
            throw new InvalidSellerItemException("Invalid Credentials. Check for merchant id or the item id");

        OrderItemDetails response = new OrderItemDetails();
        response.setItem(details);  // setting the item
        response.setAmountWithoutTax(mapping.getAmount());  // setting the amount
        response.setTotalAmount(mapping.getTotalCost());  // setting the amount with the tax breakdowns
        response.setQuantity(request.getQuantity());  // setting the requested qty
        response.setCreatedOn(LocalDateTime.now());    // setting the creation date
        response.setModifiedOn(LocalDateTime.now());  // setting the modified date

        Double currentAmount = mapping.getAmount();
        response.setCgstAmount(currentAmount * (details.getCgst()) / 100);  // adding the cgst amount
        response.setSgstAmount(currentAmount * (details.getSgst()) / 100);  // adding the sgst amount
        response.setIgstAmount(currentAmount * (details.getIgst()) / 100);  // adding the igst amount
        response.setVatAmount(currentAmount * (details.getVat()) / 100);  // adding the vat amount
        response.setCessAmount(currentAmount * (details.getCess()) / 100);  // adding the cess amount

        return response;  // returning the order item details object
    }

    /**
     * method - return the grand total of the current order
     * @param items the list of items inside the current order
     * @return the grand total of the current order
     */
    public Double getOrderGrandTotal(List<OrderItemDetails> items){
        Double total = 0.0;
        for(OrderItemDetails itemDetails : items)
            total = total + itemDetails.getTotalAmount();  // adding to the total amount
        return total;  // returning the total amount
    }

    /**
     * method - return the map of the amounts for the order details
     * @param items the list of all the items for the given order
     * @return the map of all the amounts
     */
    public Map<String, Double> getOrderDetailsAmounts(List<OrderItemDetails> items){
        Map<String, Double> amounts = new HashMap<>();

        Double amountWithoutTax = items.stream()
                .filter(item -> item.getAmountWithoutTax() != null)
                .mapToDouble(OrderItemDetails::getAmountWithoutTax)
                .sum();
        amounts.put("amount_without_tax", amountWithoutTax);  // setting the amount without tax

        Double totalAmount = items.stream()
                .filter(item -> item.getAmountWithoutTax() != null)
                .mapToDouble(OrderItemDetails::getAmountWithoutTax)
                .sum();
        amounts.put("total_amount", totalAmount);  // setting the total amount with tax

        Double totalTaxAmounts = totalAmount - amountWithoutTax;
        amounts.put("tax_amount", totalTaxAmounts);  // setting the total tax amounts

        return amounts;  // returning the map
    }

    /**
     * Generates an entry for tracking the current order status journey for the given order
     *
     * @param order the order object containing the details of the order
     * @return the order status object
     */
    public OrderStatusHistory createOrderStatusHistory(Order order){
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setOrder(order);  // setting the order object
        orderStatusHistory.setStatus(order.getCurrentStatus());  // setting the status
        orderStatusHistory.setStatusDate(LocalDateTime.now());  // setting the time of the change of the status

        orderStatusHistory.setRemarks(String.valueOf(OrderStatus.valueOf(order.getCurrentStatus())));  // setting the remarks
        return orderStatusHistory;   // returning the status object
    }

}
