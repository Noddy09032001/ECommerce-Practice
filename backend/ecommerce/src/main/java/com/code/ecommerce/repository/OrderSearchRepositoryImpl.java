package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.orderSearch.OrderSearchRequest;
import com.code.ecommerce.dto.response.UserDetailsResponse;
import com.code.ecommerce.dto.response.orderSearch.OrderItemResponse;
import com.code.ecommerce.dto.response.orderSearch.OrderSearchResponse;
import com.code.ecommerce.dto.response.orderSearch.OrderStatusHistoryResponse;
import com.code.ecommerce.dto.response.orderSearch.SellerOrderResponse;
import com.code.ecommerce.pojo.Seller;
import com.code.ecommerce.pojo.UserDetails;
import com.code.ecommerce.pojo.orders.Order;
import com.code.ecommerce.pojo.orders.OrderDetails;
import com.code.ecommerce.pojo.orders.OrderItemDetails;
import com.code.ecommerce.pojo.orders.OrderStatusHistory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderSearchRepositoryImpl implements OrderSearchRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(OrderSearchRepositoryImpl.class);  // getting the logger

    @Override
    public Page<OrderSearchResponse> searchOrder(OrderSearchRequest request) {
        logger.info("Inside the method for searching the order based on given parameters");
        try{

            List<Predicate> predicates = new ArrayList<>();  // storing the conditionally criteria for building the query

            Session session = entityManager.unwrap(Session.class);  // getting the session object
            CriteriaBuilder cb = session.getCriteriaBuilder();   // getting the criteria builder
            CriteriaQuery<Order> cq = cb.createQuery(Order.class);

            Root<Order> order = cq.from(Order.class);  // creating the root object

            // getting the complete order details including user, items and status histories
            order.fetch("user", JoinType.LEFT);
            order.fetch("orderDetails", JoinType.LEFT);
            order.fetch("orderItems", JoinType.LEFT);
            order.fetch("orderStatuses", JoinType.LEFT);

            Join<Order, UserDetails> user = order.join("user", JoinType.LEFT);  // getting the user details related to the order
            Join<Order, OrderDetails> details = order.join("orderDetails", JoinType.LEFT);   // getting the order details related to the order
            Join<Order, OrderItemDetails> items = order.join("orderItems", JoinType.LEFT);  // getting the items related to the order
            Join<Order, OrderStatusHistory> statuses = order.join("orderStatuses", JoinType.LEFT);  // getting the statuses related to the order

            // username filter for the order
            if (request.getUserName() != null && !request.getUserName().isBlank()) {
                predicates.add(cb.like(cb.lower(user.get("userName")), "%" + request.getUserName().toLowerCase() + "%"));
            }

            // email filtering
            if (request.getEmail() != null && !request.getEmail().isBlank()) {
                predicates.add(cb.like(cb.lower(user.get("email")), "%" + request.getEmail().toLowerCase() + "%"));
            }

            // filtering based on the order id
            if (request.getSearchText() != null && !request.getSearchText().isBlank()) {
                predicates.add(cb.like(cb.lower(order.get("orderId")), "%" + request.getSearchText().toLowerCase() + "%"));
            }

            // creating the min amount filter
            if (request.getMinOrderAmount() != null) {
                predicates.add(cb.ge(order.get("grandTotal"), request.getMinOrderAmount()));
            }

            // creating the max amount filter
            if (request.getMaxOrderAmount() != null) {
                predicates.add(cb.le(order.get("grandTotal"), request.getMaxOrderAmount()));
            }

            // creating the from date filter
            if (request.getFromDate() != null) {
                predicates.add(cb.greaterThanOrEqualTo(order.get("createdOn"), request.getFromDate()));
            }

            // creating the to date filter
            if (request.getToDate() != null) {
                predicates.add(cb.lessThanOrEqualTo(order.get("createdOn"), request.getToDate()));
            }

            // applying all the predicates
            cq.where(predicates.toArray(new Predicate[0]));

            // sorting filtering criteria based on pricing of the order
            if ("PRICE_ASC".equals(request.getSortBy())) {
                cq.orderBy(cb.asc(order.get("grandTotal")));
            }

            else if ("PRICE_DESC".equals(request.getSortBy())) {
                cq.orderBy(cb.desc(order.get("grandTotal")));
            }

            else {
                cq.orderBy(cb.desc(order.get("createdOn")));
            }


            cq.distinct(true);  // avoiding duplicates

            TypedQuery<Order> query = session.createQuery(cq);  // executing the query for pagination
            query.setFirstResult(request.getPage() * request.getSize());

            query.setMaxResults(request.getSize());  // setting the max paginated results
            List<Order> orders = query.getResultList();  // getting all the orders

            List<OrderSearchResponse> data = new ArrayList<>();  // storing the transformed response

            // converting each order entity to response dto
            for (Order entity : orders) {

                OrderSearchResponse response = new OrderSearchResponse();
                response.setOrderId(entity.getOrderId());  // setting the order id
                response.setCurrentStatus(entity.getCurrentStatus());  // setting the current status for the order
                response.setTotalItems(entity.getTotalItems());  // setting the total items for the order
                response.setGrandTotal(entity.getGrandTotal());  // setting the grand total of the order
                response.setCreatedOn(entity.getCreatedOn());   // setting the order creation date
                response.setModifiedOn(entity.getModifiedOn());  // setting the order modification date
                response.setCreatedBy(entity.getCreatedBy());
                response.setModifiedBy(entity.getModifiedBy());

                UserDetailsResponse customer = new UserDetailsResponse();  // creating the customer response
                customer.setName(entity.getUser().getName());  // setting the name of the user
                customer.setEmail(entity.getUser().getEmail());   // setting the email of the user

                response.setCustomer(customer);  // adding the customer details to the order response

                Map<String, SellerOrderResponse> sellerMap = new LinkedHashMap<>();  // grouping all items by seller

                for (OrderItemDetails orderItem : entity.getOrderItems()) {

                    Seller seller = entity.getOrderDetails().getSeller();   // getting the current merchant details
                    SellerOrderResponse sellerResponse = sellerMap.computeIfAbsent(seller.getSellerId(), id -> {

                                        SellerOrderResponse s = new SellerOrderResponse();  // generating the merchant item response
                                        s.setSellerId(seller.getSellerId());  // setting the merchant id
                                        s.setSellerName(seller.getSellerName());  // setting the merchant name
                                        s.setAmount(entity.getOrderDetails().getAmount());  // setting the order amount without taxes
                                        s.setTotalTaxInAmount(entity.getOrderDetails().getTotalTaxInAmount());  // setting the total tax amount
                                        s.setTotalOrderAmount(entity.getOrderDetails().getTotalOrderAmount());  // setting the amount with taxes
                                        s.setCity(entity.getOrderDetails().getCity());  // setting the city details of the merchant
                                        s.setState(entity.getOrderDetails().getState());  // setting the state details of the merchant
                                        s.setAddress(entity.getOrderDetails().getAddress());  // setting the address of the merchant
                                        return s;  // returning the object
                                    });


                    OrderItemResponse itemResponse = new OrderItemResponse();  // creating the item response for the merchant

                    itemResponse.setItemId(orderItem.getItem().getItemId());  // setting the item id
                    itemResponse.setItemName(orderItem.getItem().getItemName());  // setting the item name
                    itemResponse.setCategory(orderItem.getItem().getCategory());  // setting the item category
                    itemResponse.setQuantity(orderItem.getQuantity());  // setting the item quantity
                    itemResponse.setAmountWithoutTax(orderItem.getAmountWithoutTax());  // setting the amount without tax
                    itemResponse.setCgstAmount(orderItem.getCgstAmount());  // setting the individual cgst amount
                    itemResponse.setSgstAmount(orderItem.getSgstAmount());  // setting the individual sgst amount
                    itemResponse.setIgstAmount(orderItem.getIgstAmount());   // setting the individual igst amount
                    itemResponse.setVatAmount(orderItem.getVatAmount());   // setting the individual vat amount
                    itemResponse.setCessAmount(orderItem.getCessAmount());  // setting the individual cess amount
                    itemResponse.setTotalAmount(orderItem.getTotalAmount());   // setting the total order amount

                    sellerResponse.getItems().add(itemResponse);   // adding the item response
                }

                response.setSellerOrders(new ArrayList<>(sellerMap.values())); // setting the seller grouped items

                List<OrderStatusHistoryResponse> statusResponses = new ArrayList<>();  // creating the status history response

                for (OrderStatusHistory status : entity.getOrderStatuses()) {

                    OrderStatusHistoryResponse statusResponse = new OrderStatusHistoryResponse();
                    statusResponse.setStatus(status.getStatus());   // setting the current status
                    statusResponse.setCreatedOn(status.getStatusDate());  // setting the date of the status being created
                    statusResponse.setRemarks(status.getRemarks());   // setting the remarks

                    statusResponses.add(statusResponse);  // adding the status object to the list of current statuses
                }

                response.setOrderStatuses(statusResponses);  // setting the status history for the order
                data.add(response);   // adding the complete response object to the final response list
            }

            // creating the count query
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
            Root<Order> countRoot = countQuery.from(Order.class);
            Join<Order, UserDetails> countUser = countRoot.join("user", JoinType.LEFT);
            List<Predicate> countPredicates = new ArrayList<>();

            // username filter
            if (request.getUserName() != null && !request.getUserName().isBlank()) {
                countPredicates.add(cb.like(cb.lower(countUser.get("userName")), "%" + request.getUserName().toLowerCase() + "%"));
            }

            // email filter
            if (request.getEmail() != null && !request.getEmail().isBlank()) {
                countPredicates.add(cb.like(cb.lower(countUser.get("email")), "%" + request.getEmail().toLowerCase() + "%"));
            }

            // order id filter
            if (request.getSearchText() != null && !request.getSearchText().isBlank()) {
                countPredicates.add(cb.like(cb.lower(countRoot.get("orderId")), "%" + request.getSearchText().toLowerCase() + "%"));
            }

            // minimum amount filter
            if (request.getMinOrderAmount() != null) {
                countPredicates.add(cb.ge(countRoot.get("grandTotal"), request.getMinOrderAmount()));
            }

            // maximum amount filter
            if (request.getMaxOrderAmount() != null) {
                countPredicates.add(cb.le(countRoot.get("grandTotal"), request.getMaxOrderAmount()));
            }

            // from date filter
            if (request.getFromDate() != null) {
                countPredicates.add(cb.greaterThanOrEqualTo(countRoot.get("createdOn"), request.getFromDate()));
            }

            // to date filter
            if (request.getToDate() != null) {
                countPredicates.add(cb.lessThanOrEqualTo(countRoot.get("createdOn"), request.getToDate()));
            }

            countQuery.select(cb.countDistinct(countRoot));  // getting all the distinct elements
            countQuery.where(countPredicates.toArray(new Predicate[0]));  // executing the query
            Long total = session.createQuery(countQuery).getSingleResult();  // getting the count of the total items

            // returning the paginated response
            return new PageImpl<>(data, PageRequest.of(request.getPage(), request.getSize()), total);

        } catch (Exception e) {
            logger.info("Error searching for orders - {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
