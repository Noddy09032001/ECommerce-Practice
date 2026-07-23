package com.code.ecommerce.service.orders;

import com.code.ecommerce.common.constants.OrderStatus;
import com.code.ecommerce.dto.requests.orders.OrderItemRequest;
import com.code.ecommerce.dto.requests.orders.OrderRequest;
import com.code.ecommerce.dto.response.orders.OrderItemPayResponse;
import com.code.ecommerce.dto.response.orders.OrderResponse;
import com.code.ecommerce.exceptions.InvalidItemException;
import com.code.ecommerce.exceptions.InvalidSellerItemException;
import com.code.ecommerce.pojo.Item;
import com.code.ecommerce.pojo.SellerItemMapping;
import com.code.ecommerce.pojo.UserDetails;
import com.code.ecommerce.pojo.orders.Order;
import com.code.ecommerce.pojo.orders.OrderDetails;
import com.code.ecommerce.pojo.orders.OrderItemDetails;
import com.code.ecommerce.pojo.orders.OrderStatusHistory;
import com.code.ecommerce.repository.*;
import com.code.ecommerce.service.users.UserDetailsService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
    private final UserDetailsService userDetailsService;
    private final OrderStatusRepository orderStatusRepository;

    @Autowired
    public OrderServiceImplementation(OrderRepository orderRepository, OrderDetailsRepository orderDetailsRepository,
                                      OrderItemDetailsRepository orderItemDetailsRepository, ItemRepository itemRepository,
                                      SellerItemMappingRepository sellerItemMappingRepository, UserDetailsService userDetailsService,
                                      OrderStatusRepository orderStatusRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.orderItemDetailsRepository = orderItemDetailsRepository;
        this.itemRepository = itemRepository;
        this.sellerItemMappingRepository = sellerItemMappingRepository;
        this.userDetailsService = userDetailsService;
        this.orderStatusRepository = orderStatusRepository;
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
    public OrderResponse createOrder(OrderRequest request) throws Exception {
        logger.info("Inside the create order method - ");
        try{
            String orderId = generateOrderId();  // getting the order id
            String orderDetailsId = this.generateOrderDetailsId();  // generating the order details id

            Order order = generateOrder(orderId);  // getting the order object
            order.setTotalItems(request.getItems().size());  // setting the total items in the order

            UserDetails userDetails = this.generateUserDetailsForOrder(request.getUser().getEmail(), request.getUser().getPhoneNumber());
            order.setUser(userDetails);  // setting the current user for the order

            Order currentOrder = orderRepository.save(order);  // saving and returning the current order id
            logger.info("The order has been saved successfully");

            // Setting and generating the order details object
            OrderDetails orderDetails = this.generateOrderDetails(orderDetailsId, request);  // getting the order details object
            orderDetails.setOrder(currentOrder);  // saving the current order inside the order details
            currentOrder.setOrderDetails(orderDetails);  // setting the order details for the order

            // setting and generating the order items
            List<OrderItemDetails> items = this.generateOrderItems(request.getItems(), currentOrder);
            Double grandOrderTotal = this.getOrderGrandTotal(items);  // getting the grand total of the order

            Map<String, Double> amounts = this.getOrderDetailsAmounts(items);  // getting the map of the amounts
            orderDetails.setTotalTaxInAmount(amounts.get("tax_amount"));
            orderDetails.setTotalOrderAmount(amounts.get("total_amount"));
            orderDetails.setAmount(amounts.get("amount_without_tax"));
            orderDetailsRepository.save(orderDetails);  // saving the order details data object

            //currentOrder.setOrderItems(items);  // setting the order items for the current order
            for (OrderItemDetails item : items) {
                currentOrder.addOrderItem(item);   // setting the order items for the current order
            }
            currentOrder.setGrandTotal(grandOrderTotal);  // setting the total amount of the current order

            orderItemDetailsRepository.saveAll(items);  // saving the list of items in the current order
            orderRepository.save(currentOrder);  // saving the updated order model

            OrderStatusHistory orderStatus = this.createOrderStatusHistory(currentOrder);  // getting the current status of the order
            orderStatusRepository.save(orderStatus);   // saving the order status
            return this.generateOrderResponse(currentOrder, items); // returning the response being generated for the order

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
    public Order fetchOrderByOrderId(String orderId) throws Exception {
        logger.info("Inside the fetch order by order id method - ");
        try{
            Order currentOrder = orderRepository.findByOrderId(orderId);  // finding the current order by the order id
            return currentOrder;
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
        order.setCurrentStatus(OrderStatus.ORDER_CREATED.toString());  // setting the current status of the order created
        return order;  // return the generated order object
    }

    /**
     * method - save and generate the order details for the current order
     *
     * @param orderDetailsId the unique id of the order details of the order
     * @return the order details object generated
     */
    public OrderDetails generateOrderDetails(String orderDetailsId, OrderRequest request){
        OrderDetails orderDetails = new OrderDetails();
        orderDetails.setOrderDetailsId(orderDetailsId);  // setting the order details id
        orderDetails.setCreatedOn(LocalDateTime.now());  // setting the created on date
        orderDetails.setModifiedOn(LocalDateTime.now());  // setting the modified on date

        // saving the shipping address of the order
        orderDetails.setCity(request.getAddress().getCity());  // setting the city
        orderDetails.setState(request.getAddress().getState());   // setting the state
        orderDetails.setAddress(request.getAddress().getStreet());  // setting the address
        return orderDetails;  // returning the order details object
    }

    /**
     * method - to fetch and generate order items list
     * @param items the items present in the request body
     * @param order the order object associated with the current items under consideration
     * @return the list of order items fetched from the data
     */
    public List<OrderItemDetails> generateOrderItems(List<OrderItemRequest> items, Order order){
        logger.info("Inside the generateOrderItems method - ");
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
        logger.info("Inside the generateOrderItemDetailsObject method - ");
        SellerItemMapping mapping = sellerItemMappingRepository.findByItemAndSeller(details.getId(), request.getSellerId());
        if(mapping == null)
            throw new InvalidSellerItemException("Invalid Credentials. Check for merchant id or the item id");

        OrderItemDetails response = new OrderItemDetails();
        response.setItem(details);  // setting the item
        response.setAmountWithoutTax(mapping.getAmount() * request.getQuantity());  // setting the amount
        response.setTotalAmount(mapping.getTotalCost() * request.getQuantity());  // setting the amount with the tax breakdowns
        response.setQuantity(request.getQuantity());  // setting the requested qty
        response.setCreatedOn(LocalDateTime.now());    // setting the creation date
        response.setModifiedOn(LocalDateTime.now());  // setting the modified date

        Double currentAmount = mapping.getAmount();
        response.setCgstAmount(currentAmount * (details.getCgst()) / 100);  // adding the cgst amount
        response.setSgstAmount(currentAmount * (details.getSgst()) / 100);  // adding the sgst amount
        response.setIgstAmount(currentAmount * (details.getIgst()) / 100);  // adding the igst amount
        response.setVatAmount(currentAmount * (details.getVat()) / 100);  // adding the vat amount
        response.setCessAmount(currentAmount * (details.getCess()) / 100);  // adding the cess amount
        response.setSeller(mapping.getSeller());  // setting the seller

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
    @Override
    public OrderStatusHistory createOrderStatusHistory(Order order){
        OrderStatusHistory orderStatusHistory = new OrderStatusHistory();
        orderStatusHistory.setOrder(order);  // setting the order object
        orderStatusHistory.setStatus(order.getCurrentStatus());  // setting the status
        orderStatusHistory.setStatusDate(LocalDateTime.now());  // setting the time of the change of the status
        orderStatusHistory.setRemarks(String.valueOf(OrderStatus.valueOf(order.getCurrentStatus())));  // setting the remarks

        return orderStatusHistory;   // returning the status object
    }

    /**
     * Fetches the user details based on the phone number and the email
     *
     * @param email the current email of the user
     * @param phoneNumber the phone number of the user
     * @return the associated user object
     */
    public UserDetails generateUserDetailsForOrder(String email, String phoneNumber){
        return userDetailsService.fetchUserByEmailAndPhoneNumber(email, phoneNumber);
    }

    /**
     * Generates the order response for the given order
     * @param order the details of the order
     * @param items the details of the items in the order
     * @return the response in the order response format
     */
    public OrderResponse generateOrderResponse(Order order, List<OrderItemDetails> items){
        OrderResponse response = new OrderResponse();
        response.setOrderId(order.getOrderId());   // setting the order id
        response.setCurrency("INR");  // setting the currency
        response.setOrderDate(order.getCreatedOn());   // setting the order creation date
        response.setGrandTotal(order.getGrandTotal());   // setting the grand total of the order
        response.setItems(this.generateOrderItemsResponse(items));   // setting the items for the order
        return response;   // returning the response object
    }

    /**
     * Generates the response of the items in the given response format
     * @param items the details of the items in the order
     * @return the response in the needed format
     */
    public List<OrderItemPayResponse> generateOrderItemsResponse(List<OrderItemDetails> items){
        List<OrderItemPayResponse> responses = new ArrayList<>();
        for(OrderItemDetails details : items){
            OrderItemPayResponse response = new OrderItemPayResponse();
            response.setItemId(details.getItem().getItemId());   // setting the item id
            response.setItemName(details.getItem().getItemName());   // setting the item name
            response.setItemDescription(details.getItem().getItemDescription());  // setting the item description
            response.setBaseAmount(details.getAmountWithoutTax());   // setting the base amount

            // calculating the total tax amount for the item
            Double totalTaxAmount = details.getCgstAmount() + details.getSgstAmount() + details.getIgstAmount() + details.getVatAmount() + details.getCessAmount();

            response.setTaxAmount(totalTaxAmount);   // setting the tax amount
            response.setTotalAmount(details.getTotalAmount());   // setting the total amount
            response.setQuantity(details.getQuantity());   // setting the quantity
            response.setTransportationCharges(0.0);   // setting the transportation charges
            response.setOtherCharges(0.0);   // setting the other charges

            responses.add(response);  // adding to the list of responses
        }
        return responses;  // returning the responses
    }

    /**
     * Generating confirmation email for order creation
     *
     * @param customerEmail the email of the user whose order it is
     * @param customerName the name of the customer
     * @param orderId the order id of the current order
     * @param orderDate the date of the order when it was confirmed and processed
     * @throws MessagingException exception if any error occurs during the message generation or sending
     */
    /*@Override
    public void sendOrderConfirmationMail(String customerEmail, String customerName, String orderId, LocalDateTime orderDate) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setFrom(""); // sender email
        helper.setTo(customerEmail); // recipient email
        helper.setSubject("Your Order Has Been Confirmed"); // email subject

        String content = """
            <html>
            <body style="font-family: Arial, Helvetica, sans-serif; color: #333333;">
                <h2>Order Confirmation</h2>
                <p>Dear %s,</p>
                <p>
                    Thank you for shopping with us.
                    We're pleased to inform you that your payment has been received successfully
                    and your order has been confirmed.
                </p>

                <h3>Order Details</h3>
                <p>
                    <strong>Order ID:</strong> %s<br>
                    <strong>Order Date:</strong> %s<br>
                    <strong>Payment Status:</strong> Confirmed
                </p>
                <p>
                    To view your complete order details and track your order,
                    simply log in to your account through our application.
                </p>

                <p>
                    Thank you for choosing us.
                </p>

                <br>
                <p>
                    Regards,<br>
                    <strong>Your Company Team</strong>
                </p>
            </body>
            </html>
            """
                .formatted(customerName, orderId, orderDate);

        helper.setText(content, true); // setting the email context and text
        javaMailSender.send(message);  // sending the email
    }*/
}
