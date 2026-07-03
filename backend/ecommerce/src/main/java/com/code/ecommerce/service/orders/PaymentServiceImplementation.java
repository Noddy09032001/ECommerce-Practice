package com.code.ecommerce.service.orders;

import com.code.ecommerce.pojo.orders.OrderItemDetails;
import com.stripe.StripeClient;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentServiceImplementation implements PaymentService{

    @Value("${stripe.secret}")
    private String stripeApiKey;

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImplementation.class);

    /**
     *
     * @param items
     * @throws Exception
     */
    @Override
    public String initiatePayment(List<OrderItemDetails> items) throws Exception {
        logger.info("Inside the initiate payment method - ");
        try{
            StripeClient client = new StripeClient(stripeApiKey);  // initialize the stripe client object
            List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();  // creating a list for adding the items

            for(OrderItemDetails item : items){
                SessionCreateParams.LineItem item1 = SessionCreateParams.LineItem.builder()
                        .setQuantity(item.getQuantity().longValue())  // setting the quantity
                        .setPriceData(
                                SessionCreateParams.LineItem.PriceData.builder()
                                        .setCurrency("inr")  // setting the currency
                                        .setUnitAmount(item.getTotalAmount().longValue())  // setting the individual unit amount
                                        .setProductData(
                                                SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                        .setName(item.getItem().getItemName())  // set item name
                                                        .setDescription(item.getItem().getItemDescription())  // set item description
                                                        .build()
                                        )
                                        .build()
                        )
                        .build();
                lineItems.add(item1);  // adding the items to the list
            }


            Map<String, String> metaDataMap = new HashMap<>();  // creating a metadata map for the stripe object
            metaDataMap.put("order-id", items.get(0).getOrder().getOrderId());  // adding the order id

            SessionCreateParams params = SessionCreateParams.builder()
                    .setSuccessUrl("http://localhost:5173/success")  // setting the success url
                    .setCancelUrl("http://localhost:5173/cancel")  // setting the cancel url
                    .setMode(SessionCreateParams.Mode.PAYMENT)  // setting the mode of payment
                    .addAllLineItem(lineItems)  // adding the line items
                    .putAllMetadata(metaDataMap)  // adding the metadata
                    .build();  // build the checkout sessions

            Session session = client.v1().checkout().sessions().create(params);  // creating the session for stripe client
            return session.getUrl();  // returning the session url

        } catch (RuntimeException e) {
            logger.info("Error creating payment session: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
