package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.orderSearch.OrderSearchRequest;
import com.code.ecommerce.dto.response.UserDetailsResponse;
import com.code.ecommerce.dto.response.orderSearch.*;
import com.code.ecommerce.exceptions.OrderSearchException;
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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class OrderSearchRepositoryImpl implements OrderSearchRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(OrderSearchRepositoryImpl.class);  // getting the logger

    /*@Override
    public Page<OrderSearchResponse> searchOrder(OrderSearchRequest request) {
        logger.info("\nInside the method for searching the order based on given parameters - ");
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
                predicates.add(cb.like(cb.lower(user.get("name")), "%" + request.getUserName().toLowerCase() + "%"));
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
                    Seller seller = orderItem.getSeller();   // getting the current merchant details
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
            throw new OrderSearchException("Unable to find the orders for the given parameters");
        }
    }*/

    /**
     * Searches orders based on the supplied filters and returns a paginated response.
     *
     * @param request the order search request
     * @return the paginated order search response
     */
    @Override
    public Page<OrderSearchResponse> searchOrder(OrderSearchRequest request) {
        logger.info("Searching orders with filters : {}", request);
        try {
            Session session = entityManager.unwrap(Session.class);

            // fetching the paginated order ids
            List<Long> orderIds = getPagedOrderIds(session, request);

            if (orderIds.isEmpty()) {
                return new PageImpl<>(Collections.emptyList(), PageRequest.of(request.getPage(), request.getSize()), 0);
            }

            // Fetching the  orders with user, order details, items and sellers
            List<Order> orders = getOrdersWithItems(session, orderIds);

            // Fetching the status history for the orders
            Map<Long, List<OrderStatusHistory>> statusMap = getOrderStatuses(session, orderIds);

            // Converting the entities into response structures
            List<OrderSearchResponse> response = orders.stream()
                    .map(order -> mapToResponse(order,
                            statusMap.getOrDefault(order.getId(), Collections.emptyList())))
                    .collect(Collectors.toList());

            // Getting the total count for pagination
            Long totalCount = getTotalCount(session, request);
            logger.info("Total Orders Found : {}", totalCount);

            return new PageImpl<>(response, PageRequest.of(request.getPage(), request.getSize()), totalCount);

        } catch (Exception ex) {

            logger.error("Error while searching orders", ex);

            throw new RuntimeException(
                    "Unable to fetch order search results",
                    ex
            );
        }
    }

    /**
     * Returns the paginated order ids after applying all search filters and sorting.
     *
     * @param session the Hibernate session
     * @param request the order search request
     * @return the paginated order ids
     */
    private List<Long> getPagedOrderIds(Session session, OrderSearchRequest request) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);

        Root<Order> order = cq.from(Order.class);
        Join<Order, UserDetails> user = order.join("user", JoinType.INNER);

        List<Predicate> predicates = buildOrderPredicates(cb, order, user, request);

        cq.select(order).distinct(true);
        cq.where(predicates.toArray(new Predicate[0]));

        // Sorting
        if ("AMOUNT_ASC".equalsIgnoreCase(request.getSortBy())) {
            cq.orderBy(cb.asc(order.get("grandTotal")));

        } else if ("AMOUNT_DESC".equalsIgnoreCase(request.getSortBy())) {
            cq.orderBy(cb.desc(order.get("grandTotal")));

        } else if ("DATE_ASC".equalsIgnoreCase(request.getSortBy())) {
            cq.orderBy(cb.asc(order.get("createdOn")));

        } else if ("DATE_DESC".equalsIgnoreCase(request.getSortBy())) {
            cq.orderBy(cb.desc(order.get("createdOn")));

        } else {
            cq.orderBy(cb.desc(order.get("createdOn")));
        }

        TypedQuery<Order> query = session.createQuery(cq);

        query.setFirstResult(request.getPage() * request.getSize());
        query.setMaxResults(request.getSize());

        return query.getResultList().stream().map(Order::getId).collect(Collectors.toList());
    }

    /**
     * Fetches the orders along with their associated items and seller details.
     *
     * @param session the Hibernate session
     * @param orderIds the order ids
     * @return the orders with item details
     */
    private List<Order> getOrdersWithItems(Session session, List<Long> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            return Collections.emptyList();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Order> cq = cb.createQuery(Order.class);
        Root<Order> order = cq.from(Order.class);

        // fetching the user details
        order.fetch("user", JoinType.LEFT);

        // fetching the order details
        order.fetch("orderDetails", JoinType.LEFT);

        // fetching the order items associated with the items
        Fetch<Order, OrderItemDetails> itemFetch =
                order.fetch("orderItems", JoinType.LEFT);

        // fetch the item
        itemFetch.fetch("item", JoinType.LEFT);

        // fetching the merchant associated with the item
        itemFetch.fetch("seller", JoinType.LEFT);


        cq.select(order).distinct(true);
        cq.where(order.get("id").in(orderIds));
        List<Order> orders = session.createQuery(cq).getResultList();
        for (Order order1 : orders) {
            System.out.println("\nOrder ID : " + order1.getId());

            if (order1.getOrderDetails() == null) {
                System.out.println("\nOrderDetails = NULL");
            } else {
                System.out.println("\nOrderDetails ID = " + order1.getOrderDetails().getId());
            }
        }

        // preserving the pagination order
        Map<Long, Order> orderMap = orders.stream().collect(Collectors.toMap(Order::getId, Function.identity()));

        List<Order> orderedOrders = new ArrayList<>();
        for (Long id : orderIds) {
            Order entity = orderMap.get(id);
            if (entity != null) {
                orderedOrders.add(entity);
            }
        }

        return orderedOrders;
    }

    /**
     * Fetches the status history for the given order ids.
     *
     * @param session the Hibernate session
     * @param orderIds the order ids
     * @return the status history grouped by order id
     */
    private Map<Long, List<OrderStatusHistory>> getOrderStatuses(Session session, List<Long> orderIds) {
        if (orderIds == null || orderIds.isEmpty()) {
            return Collections.emptyMap();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery<OrderStatusHistory> cq = cb.createQuery(OrderStatusHistory.class);
        Root<OrderStatusHistory> status = cq.from(OrderStatusHistory.class);

        status.fetch("order", JoinType.INNER);
        cq.select(status);
        cq.where(status.get("order").get("id").in(orderIds));
        cq.orderBy(cb.asc(status.get("statusDate")));

        List<OrderStatusHistory> statuses = session.createQuery(cq).getResultList();

        return statuses.stream().collect(Collectors.groupingBy(s -> s.getOrder().getId(),
                        LinkedHashMap::new, Collectors.toList()));
    }

    /**
     * Builds the predicates for the order search query.
     *
     * @param cb criteria builder
     * @param order order root
     * @param user user join
     * @param request search request
     * @return list of predicates
     */
    private List<Predicate> buildOrderPredicates(CriteriaBuilder cb, Root<Order> order, Join<Order, UserDetails> user, OrderSearchRequest request) {
        List<Predicate> predicates = new ArrayList<>();
        if (request.getUserName() != null && !request.getUserName().isBlank()) {
            predicates.add(cb.like(cb.lower(user.get("name")), "%" + request.getUserName().toLowerCase() + "%"));
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()) {
            predicates.add(cb.like(cb.lower(user.get("email")), "%" + request.getEmail().toLowerCase() + "%"));
        }

        if (request.getSearchText() != null && !request.getSearchText().isBlank()) {
            predicates.add(cb.like(cb.lower(order.get("orderId")), "%" + request.getSearchText().toLowerCase() + "%"));
        }

        if (request.getMinOrderAmount() != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.get("grandTotal"), request.getMinOrderAmount()));
        }

        if (request.getMaxOrderAmount() != null) {
            predicates.add(cb.lessThanOrEqualTo(order.get("grandTotal"), request.getMaxOrderAmount()));
        }

        if (request.getFromDate() != null) {
            predicates.add(cb.greaterThanOrEqualTo(order.get("createdOn"), request.getFromDate()));
        }

        if (request.getToDate() != null) {
            predicates.add(cb.lessThanOrEqualTo(order.get("createdOn"), request.getToDate()));
        }

        return predicates;
    }

    /**
     * Returns the total number of orders matching the search filters.
     *
     * @param session the Hibernate session
     * @param request the search request
     * @return total matching orders
     */
    private Long getTotalCount(Session session, OrderSearchRequest request) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Order> order = cq.from(Order.class);
        Join<Order, UserDetails> user = order.join("user", JoinType.LEFT);

        List<Predicate> predicates = buildOrderPredicates(cb, order, user, request);
        cq.select(cb.countDistinct(order));
        cq.where(predicates.toArray(new Predicate[0]));

        return session.createQuery(cq).getSingleResult();
    }

    /**
     * Converts the Order entity into the search response DTO.
     *
     * @param order the order entity
     * @param statuses the status history of the order
     * @return the mapped order response
     */
    private OrderSearchResponse mapToResponse(Order order, List<OrderStatusHistory> statuses) {
        OrderSearchResponse response = new OrderSearchResponse();

        response.setOrderId(order.getOrderId());
        response.setCurrentStatus(order.getCurrentStatus());
        response.setTotalItems(order.getTotalItems());
        response.setGrandTotal(order.getGrandTotal());
        response.setCreatedOn(order.getCreatedOn());
        response.setModifiedOn(order.getModifiedOn());
        response.setCreatedBy(order.getCreatedBy());
        response.setModifiedBy(order.getModifiedBy());

        // Customer Details
        UserDetailsResponse customer = new UserDetailsResponse();
        customer.setName(order.getUser().getName());
        customer.setEmail(order.getUser().getEmail());

        response.setCustomer(customer);

        OrderDetails details = order.getOrderDetails();
        OrderDetailsResponse detailsResponse = new OrderDetailsResponse();

        if (details != null) {
            detailsResponse.setOrderDetailsId(details.getOrderDetailsId());  // setting the order details id
            detailsResponse.setAmount(details.getAmount());  // setting the amount
            detailsResponse.setTotalTaxInAmount(details.getTotalTaxInAmount());  // setting the tax amount
            detailsResponse.setTotalOrderAmount(details.getTotalOrderAmount());

            String payMode = "";

            if(details.getPaymentMode() == null)
                payMode = "CARD";

            detailsResponse.setPaymentMode(payMode);
            detailsResponse.setCity(details.getCity());
            detailsResponse.setState(details.getState());
            detailsResponse.setAddress(details.getAddress());
        }

        response.setOrderDetails(detailsResponse);  // setting the order details response

        // grouping the items by the merchants associated
        Map<String, SellerOrderResponse> sellerMap = new LinkedHashMap<>();

        for (OrderItemDetails orderItem : order.getOrderItems()) {
            Seller seller = orderItem.getSeller();
            SellerOrderResponse sellerResponse = sellerMap.computeIfAbsent(
                            seller.getSellerId(), id -> mapSellerResponse(order, seller));

            OrderItemResponse itemResponse = new OrderItemResponse();

            itemResponse.setItemId(orderItem.getItem().getItemId());
            itemResponse.setItemName(orderItem.getItem().getItemName());
            itemResponse.setCategory(orderItem.getItem().getCategory());

            itemResponse.setQuantity(orderItem.getQuantity());

            itemResponse.setAmountWithoutTax(orderItem.getAmountWithoutTax());

            itemResponse.setCgstAmount(orderItem.getCgstAmount());
            itemResponse.setSgstAmount(orderItem.getSgstAmount());
            itemResponse.setIgstAmount(orderItem.getIgstAmount());
            itemResponse.setVatAmount(orderItem.getVatAmount());
            itemResponse.setCessAmount(orderItem.getCessAmount());

            itemResponse.setTotalAmount(orderItem.getTotalAmount());
            sellerResponse.getItems().add(itemResponse);
        }

        response.setSellerOrders(new ArrayList<>(sellerMap.values()));

        // getting the status history for the order
        List<OrderStatusHistoryResponse> statusResponses = statuses == null
                        ? Collections.emptyList()
                        : statuses.stream()
                        .sorted(Comparator.comparing(OrderStatusHistory::getStatusDate))
                        .map(this::mapStatusResponse).toList();

        response.setOrderStatuses(statusResponses);  // setting the order status response and adding it to the status list
        return response;   // returning the response structure
    }

    /**
     * Creates the seller response for an order.
     *
     * @param order the order entity
     * @param seller the seller entity
     * @return the seller response
     */
    private SellerOrderResponse mapSellerResponse(Order order, Seller seller) {
        SellerOrderResponse response = new SellerOrderResponse();
        response.setSellerId(seller.getSellerId());
        response.setSellerName(seller.getSellerName());
        OrderDetails details = order.getOrderDetails();

        if (details != null) {
            response.setAmount(details.getAmount());
            response.setTotalTaxInAmount(details.getTotalTaxInAmount());
            response.setTotalOrderAmount(details.getTotalOrderAmount());
            response.setAddress(details.getAddress());
            response.setCity(details.getCity());
            response.setState(details.getState());
        }

        response.setItems(new ArrayList<>());
        return response;
    }

    /**
     * Converts the order status history entity into its response DTO.
     *
     * @param status the order status history entity
     * @return the mapped status response
     */
    private OrderStatusHistoryResponse mapStatusResponse(OrderStatusHistory status) {
        OrderStatusHistoryResponse response = new OrderStatusHistoryResponse();
        response.setStatus(status.getStatus());
        response.setCreatedOn(status.getStatusDate());
        response.setRemarks(status.getRemarks());

        return response;
    }
}
