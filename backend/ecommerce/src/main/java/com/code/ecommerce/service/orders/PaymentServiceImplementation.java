package com.code.ecommerce.service.orders;

import com.code.ecommerce.common.constants.OrderStatus;
import com.code.ecommerce.common.constants.PaymentStatus;
import com.code.ecommerce.dto.requests.payments.PaymentItem;
import com.code.ecommerce.dto.requests.payments.PaymentRequest;
import com.code.ecommerce.dto.response.orderSearch.OrderStatusHistoryResponse;
import com.code.ecommerce.exceptions.PaymentException;
import com.code.ecommerce.pojo.UserDetails;
import com.code.ecommerce.pojo.orders.Order;
import com.code.ecommerce.pojo.orders.OrderItemDetails;
import com.code.ecommerce.pojo.orders.OrderPaymentDetails;
import com.code.ecommerce.pojo.orders.OrderStatusHistory;
import com.code.ecommerce.repository.OrderPaymentDetailsRepository;
import com.code.ecommerce.repository.OrderRepository;
import com.code.ecommerce.repository.OrderStatusRepository;
import com.stripe.StripeClient;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.PaymentIntent;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentServiceImplementation implements PaymentService{

    @Value("${stripe.secret}")
    private String stripeApiKey;

    private final OrderService orderService;
    private final OrderStatusRepository orderStatusRepository;
    private final OrderRepository orderRepository;
    private final OrderPaymentDetailsRepository orderPaymentDetailsRepository;
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImplementation.class);

    @Autowired
    public PaymentServiceImplementation(OrderService orderService, OrderStatusRepository orderStatusRepository, OrderRepository orderRepository,
                                        OrderPaymentDetailsRepository orderPaymentDetailsRepository) {
        this.orderService = orderService;
        this.orderStatusRepository = orderStatusRepository;
        this.orderRepository = orderRepository;
        this.orderPaymentDetailsRepository = orderPaymentDetailsRepository;
    }

    /**
     * Initiates a Stripe checkout session for the provided payment request.
     *
     * @param request the payment request containing the order and item details
     * @return the Stripe checkout session URL
     * @throws Exception if the payment session cannot be created
     */
    @Override
    public String initiatePayment(PaymentRequest request) throws Exception {
        logger.info("Inside the initiate payment method - ");
        try{
            StripeClient client = new StripeClient(stripeApiKey);  // initialize the stripe client object
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();  // creating a list for adding the items

            for(PaymentItem item : request.getItems()){
                SessionCreateParams.LineItem item1 = SessionCreateParams.LineItem.builder()
                        .setQuantity(item.getQuantity().longValue())  // setting the quantity
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("inr")  // setting the currency
                                        .setUnitAmount(item.getBaseAmount().longValue())  // setting the individual unit amount
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getItemName())  // setting the item name
                                                        .setUnitLabel(item.getItemId())  // setting the item unique label id
                                                        .setDescription(item.getItemDescription())  // setting item description
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
                lineItems.add(item1);  // adding the items to the list
            }


            Map<String, String> metaDataMap = new HashMap<>();  // creating a metadata map for the stripe object
            metaDataMap.put("order-id", request.getOrderId());  // adding the order id

            SessionCreateParams params = SessionCreateParams.builder()
                    .setSuccessUrl("http://localhost:3000/orders")  // setting the success url
                    .setCancelUrl("http://localhost:3000/cancel")  // setting the cancel url
                    .setMode(SessionCreateParams.Mode.PAYMENT)  // setting the mode of payment
                    .addAllLineItem(lineItems)  // adding the line items
                    .putAllMetadata(metaDataMap)  // adding the metadata
                    .build();  // build the checkout sessions

            Session session = client.v1().checkout().sessions().create(params);  // creating the session for stripe client
            logger.info("Checkout URL: {}", session.getUrl());
            logger.info("Session ID: {}", session.getId());
            return session.getUrl();  // returning the session url

        } catch (RuntimeException e) {
            logger.info("Error creating payment session: {}", e.getMessage());
            throw new PaymentException("Payment session url could not be generated");
        }
    }

    /**
     *
     * @param event
     * @throws Exception
     */
    @Override
    public void generateStripeEvents(Event event) throws Exception{
        logger.info("Received stripe event of the type: {}", event.getType());
        switch (event.getType()) {
            // payment completed successfully
            case "checkout.session.completed": {
                Session session = (Session) event.getDataObjectDeserializer().getObject().orElseThrow();
                String orderId = session.getMetadata().get("order-id");   // getting the order id from the metadata object
                logger.info("Checkout completed for Order: {}", orderId);

                Order currentOrder = orderService.fetchOrderByOrderId(orderId);   // getting the order from the current order id
                currentOrder.setCurrentStatus(OrderStatus.ORDER_PLACED.toString());  // modifying the current status of the order
                orderRepository.save(currentOrder);   // saving the updated order

                OrderStatusHistory history = orderService.createOrderStatusHistory(currentOrder);  // creating the order status history
                orderStatusRepository.save(history);   // saving the current status history

                // getting the payment details object and saving the payment history
                OrderPaymentDetails details = this.generatePaymentDetails(session, currentOrder, PaymentStatus.SUCCESS);
                orderPaymentDetailsRepository.save(details);   // saving the payment details history

                UserDetails userDetails = currentOrder.getUser();  // getting the current user
                ///orderService.sendOrderConfirmationMail(userDetails.getEmail(), userDetails.getName(), orderId, currentOrder.getCreatedOn());  // sending the confirmation email

                // TODO:
                // Reduce inventory

                break;
            }

            // payment not completed by the customer
            case "checkout.session.expired": {
                Session session = (Session) event.getDataObjectDeserializer().getObject().orElseThrow();
                String orderId = session.getMetadata().get("order-id");  // getting the order id
                logger.info("Checkout expired for Order: {}", orderId);

                // TODO:
                // Mark order as PAYMENT_EXPIRED
                // Release reserved inventory

                break;
            }

            // payment intent successfully
            case "payment_intent.succeeded": {
                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().orElseThrow();
                logger.info("Payment Intent Succeeded: {}", paymentIntent.getId());

                // TODO:
                // Save payment intent
                // Update payment records

                break;
            }

            // payment failed
            case "payment_intent.payment_failed": {
                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().orElseThrow();
                logger.info("Payment Failed: {}", paymentIntent.getId());

                // TODO:
                // Mark payment failed
                // Notify customer
                // Update order status

                break;
            }

            // payment intent canceled
            case "payment_intent.canceled": {
                PaymentIntent paymentIntent = (PaymentIntent) event.getDataObjectDeserializer().getObject().orElseThrow();
                logger.info("Payment Cancelled: {}", paymentIntent.getId());

                // TODO:
                // Mark payment cancelled

                break;
            }

            // refund initiation
            case "charge.refunded": {
                Charge charge = (Charge) event.getDataObjectDeserializer().getObject().orElseThrow();
                logger.info("Charge Refunded: {}", charge.getId());

                // TODO:
                // Mark order refunded
                // Save refund information

                break;
            }

            default:
                logger.info("Unhandled Stripe Event: {}", event.getType());
        }
    }


    /**
     * method to generate unique order id for the given payment
     * @return the generated payment id
     */
    public String generatePaymentId(){
        return "PAY-" + UUID.randomUUID().toString().replace("-", "").substring(0, 16).toUpperCase();
    }


    /**
     *
     * @param session
     * @param order
     * @param status
     * @return
     */
    public OrderPaymentDetails generatePaymentDetails(Session session, Order order, PaymentStatus status){
        OrderPaymentDetails payment = new OrderPaymentDetails();
        payment.setPaymentId(generatePaymentId());   // setting the payment id
        payment.setStripeSessionId(session.getId());   // setting the stripe session id
        payment.setStripePaymentIntentId(session.getPaymentIntent());  // setting the stripe payment intent id
        payment.setOrder(order);  // setting the current order

        payment.setAmount(session.getAmountTotal() / 100.0);  // setting the amount of payment
        payment.setCurrency(session.getCurrency().toUpperCase());  // setting the currency

        payment.setPaymentMode("CARD");  // setting the mode of payment
        payment.setPaymentStatus(status.toString());  // setting the payment status

        payment.setPaymentDate(LocalDateTime.now());  // setting the payment creation date
        payment.setCreatedOn(LocalDateTime.now());  // setting the creation date
        payment.setModifiedOn(LocalDateTime.now());  // setting the modification date

        return payment;  // returning the generated payment details object
    }
}
