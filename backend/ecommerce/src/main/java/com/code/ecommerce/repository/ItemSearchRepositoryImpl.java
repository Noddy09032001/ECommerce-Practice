package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.itemSearch.ItemSearchRequest;
import com.code.ecommerce.dto.response.ItemResponse;
import com.code.ecommerce.dto.response.SellerItemResponse;
import com.code.ecommerce.exceptions.ItemSearchException;
import com.code.ecommerce.pojo.Item;
import com.code.ecommerce.pojo.Seller;
import com.code.ecommerce.pojo.SellerItemMapping;
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
public class ItemSearchRepositoryImpl implements ItemSearchRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(ItemSearchRepositoryImpl.class);  // getting the logger

    /**
     * Returns the item catalogue based on the search parameters
     *
     * @param request the request containing the details of the search parameters
     * @return the item catalogue based on the search filters
     */
    @Override
    public Page<ItemResponse> searchItems(ItemSearchRequest request) {
        logger.info("Searching items with filters : {}", request);
        try {
            Session session = entityManager.unwrap(Session.class);
            List<Long> itemIds = getPagedItemIds(session, request);   // fetching only paginated item ids for all the items

            if (itemIds.isEmpty()) {
                return Page.empty(PageRequest.of(request.getPage(), request.getSize()));
            }

            List<Item> items = getItemsWithSellers(session, itemIds);  // fetching the complete item details with merchants associated to the items
            Long totalCount = getTotalCount(session, request);  // Total count for pagination

            // Convert into the response format structure defined
            List<ItemResponse> response = items.stream()
                    .map(item -> mapToResponse(item, request))
                    .toList();

            return new PageImpl<>(response, PageRequest.of(request.getPage(), request.getSize()), totalCount);
        } catch (Exception ex) {
            logger.error("Error while searching items", ex);
            throw new ItemSearchException("Unable to fetch item catalogue");
        }
    }

    /**
     * Returns the paginated list of item IDs after applying all search filters and sorting.
     *
     * @param session the Hibernate session
     * @param request the search request containing filters, sorting, and pagination details
     * @return the list of paginated item IDs
     */
    private List<Long> getPagedItemIds(Session session, ItemSearchRequest request) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> item = cq.from(Item.class);

        Join<Item, SellerItemMapping> mapping = item.join("sellers", JoinType.INNER);   // getting the item and the merchant mapping details
        Join<SellerItemMapping, Seller> seller = mapping.join("seller", JoinType.INNER);   // getting the merchant details

        List<Predicate> predicates = buildItemPredicates(cb, item, mapping, seller, request);  // generating the item predicates
        cq.select(item).distinct(true);   // getting the distinct items only
        cq.where(predicates.toArray(new Predicate[0]));  // applying the item predicates to the filtering query

        // sorting by the cheapest seller pricing
        if ("PRICE_ASC".equalsIgnoreCase(request.getSortBy())) {
            Subquery<Double> minPrice = cq.subquery(Double.class);
            Root<SellerItemMapping> subRoot = minPrice.from(SellerItemMapping.class);
            minPrice.select(cb.min(subRoot.get("amount")));
            minPrice.where(cb.equal(subRoot.get("item"), item), cb.isTrue(subRoot.get("active")));
            cq.orderBy(cb.asc(minPrice));
        }
        else if ("PRICE_DESC".equalsIgnoreCase(request.getSortBy())) {
            Subquery<Double> minPrice = cq.subquery(Double.class);
            Root<SellerItemMapping> subRoot = minPrice.from(SellerItemMapping.class);
            minPrice.select(cb.min(subRoot.get("amount")));
            minPrice.where(cb.equal(subRoot.get("item"), item), cb.isTrue(subRoot.get("active")));
            cq.orderBy(cb.desc(minPrice));
        }
        else {
            cq.orderBy(cb.asc(item.get("itemName")));
        }

        TypedQuery<Item> query = session.createQuery(cq);  // executing the query
        query.setFirstResult(request.getPage() * request.getSize());
        query.setMaxResults(request.getSize());  // setting the max results per page
        return query.getResultList().stream().map(Item::getId).toList();   // converting the result set into a list
    }

    /**
     * Fetches the items along with their associated sellers for the given item IDs.
     *
     * @param session the Hibernate session
     * @param itemIds the list of item IDs to fetch
     * @return the list of items with seller details
     */
    private List<Item> getItemsWithSellers(Session session, List<Long> itemIds) {
        if (itemIds == null || itemIds.isEmpty()) {
            return Collections.emptyList();
        }

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Item> cq = cb.createQuery(Item.class);
        Root<Item> item = cq.from(Item.class);

        Fetch<Item, SellerItemMapping> sellerFetch = item.fetch("sellers", JoinType.LEFT);  // Fetch SellerItemMapping
        sellerFetch.fetch("seller", JoinType.LEFT);  // Fetch Seller details

        cq.select(item).distinct(true);
        cq.where(item.get("id").in(itemIds));
        List<Item> items = session.createQuery(cq).getResultList();
        Map<Long, Item> itemMap = items.stream().collect(Collectors.toMap(Item::getId, Function.identity()));   // Preserve the ordering returned by getPagedItemIds().
        List<Item> orderedItems = new ArrayList<>();

        for (Long id : itemIds) {
            Item entity = itemMap.get(id);
            if (entity != null) {
                orderedItems.add(entity);
            }
        }

        return orderedItems;
    }

    /**
     * Builds all the dynamic predicates for the item search query.
     *
     * @param cb       Criteria builder
     * @param item     Item root
     * @param mapping  SellerItemMapping join
     * @param seller   Seller join
     * @param request  Search request
     * @return List of predicates
     */
    private List<Predicate> buildItemPredicates(CriteriaBuilder cb, Root<Item> item, Join<Item, SellerItemMapping> mapping,
            Join<SellerItemMapping, Seller> seller, ItemSearchRequest request) {
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(cb.isTrue(item.get("active")));  // Return only active items
        predicates.add(cb.isTrue(mapping.get("active")));  // Return only active seller-item mappings
        predicates.add(cb.isTrue(seller.get("active")));  // Return only active sellers

        // Category filter
        if (request.getCategories() != null && !request.getCategories().isEmpty()) {
            predicates.add(item.get("category").in(request.getCategories()));
        }

        // Seller filter
        if (request.getSellerIds() != null && !request.getSellerIds().isEmpty()) {
            predicates.add(seller.get("sellerId").in(request.getSellerIds()));
        }

        // Minimum price filter
        if (request.getMinPrice() != null) {
            predicates.add(cb.greaterThanOrEqualTo(mapping.get("amount"), request.getMinPrice()));
        }

        // Maximum price filter
        if (request.getMaxPrice() != null) {
            predicates.add(cb.lessThanOrEqualTo(mapping.get("amount"), request.getMaxPrice()));
        }

        // Search by item name (case-insensitive)
        if (request.getSearchText() != null && !request.getSearchText().trim().isEmpty()) {
            predicates.add(cb.like(cb.lower(item.get("itemName")), "%" + request.getSearchText().trim().toLowerCase() + "%"));
        }

        return predicates;
    }



    /**
     * Returns the total number of items matching the search filters.
     *
     * @param session the Hibernate session
     * @param request the search request containing filter criteria
     * @return the total count of matching items
     */
    private Long getTotalCount(Session session, ItemSearchRequest request) {
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Item> item = cq.from(Item.class);
        Join<Item, SellerItemMapping> mapping = item.join("sellers", JoinType.INNER);
        Join<SellerItemMapping, Seller> seller = mapping.join("seller", JoinType.INNER);
        List<Predicate> predicates = buildItemPredicates(cb, item, mapping, seller, request);
        cq.select(cb.countDistinct(item));
        cq.where(predicates.toArray(new Predicate[0]));
        return session.createQuery(cq).getSingleResult();
    }

    /**
     * Converts an Item entity into its response DTO with the filtered seller details.
     *
     * @param item the item entity
     * @param request the search request containing seller and price filters
     * @return the mapped item response
     */
    private ItemResponse mapToResponse(Item item, ItemSearchRequest request) {
        ItemResponse response = new ItemResponse();
        response.setId(item.getId());  // setting the id of the item
        response.setName(item.getItemName());  // setting the name of the item
        response.setDescription(item.getItemDescription());  // setting the description of the item
        response.setCategory(item.getCategory());  // setting the item category
        response.setSku(item.getSku());  // setting the sku of the item
        response.setActive(item.getActive());  // setting whether the item is active or not
        response.setCreatedOn(item.getCreatedOn());  // setting the created on date
        response.setModifiedOn(item.getModifiedOn());  // setting the modified on date
        response.setCgst(item.getCgst());  // setting the cgst value
        response.setSgst(item.getSgst());  // setting the sgst value
        response.setIgst(item.getIgst());   // setting the igst value
        response.setVat(item.getVat());  // setting the vat value
        response.setCess(item.getCess());   // setting the cess values

        // filter the seller list according to the filters
        List<SellerItemResponse> sellerResponses = item.getSellers().stream()
                        .filter(mapping -> Boolean.TRUE.equals(mapping.getActive()))  // Active mapping
                        .filter(mapping -> Boolean.TRUE.equals(mapping.getSeller().getActive()))   // Active seller

                        // Seller filter
                        .filter(mapping ->
                                request.getSellerIds() == null || request.getSellerIds().isEmpty() || request.getSellerIds().contains(mapping.getSeller().getSellerId())
                        )
                        .filter(mapping -> request.getMinPrice() == null || mapping.getAmount() >= request.getMinPrice())  // Min price
                        .filter(mapping -> request.getMaxPrice() == null || mapping.getAmount() <= request.getMaxPrice())  // Max price
                        .sorted(Comparator.comparing(SellerItemMapping::getAmount))  // Cheapest seller first
                        .map(this::mapSellerResponse)  // converting to the needed response format structure
                        .toList();  // converting to the list

        response.setMerchants(sellerResponses);  // setting the merchant details
        return response;  // returning the response details
    }


    /**
     * Converts SellerItemMapping entity into SellerItemResponse.
     *
     * @param mapping SellerItemMapping entity
     * @return SellerItemResponse DTO
     */
    private SellerItemResponse mapSellerResponse(SellerItemMapping mapping) {
        SellerItemResponse response = new SellerItemResponse();
        response.setId(mapping.getSeller().getId());   // getting the id of the merchant
        response.setName(mapping.getSeller().getSellerName());  // name of the merchant
        response.setAmount(mapping.getAmount());   // base amount before taxes
        response.setFinalAmount(mapping.getTotalCost());  // final amount after taxes/charges
        response.setOtherCharges(mapping.getOtherCharges());  // other charges
        response.setTransportationCharges(mapping.getTransportationCharges());  // transportation charges
        response.setQuantity(mapping.getAvailableQuantity());  // available stock of the item at the merchant
        return response;  // returning the response object
    }


    /*@Override
    public Page<ItemResponse> searchItems(ItemSearchRequest request) {
        logger.info("Inside the item search method - ");
        try{
            List<Predicate> predicates = new ArrayList<>();  // storing the conditionally criteria for building the query

            Session session = entityManager.unwrap(Session.class);  // getting the session object
            CriteriaBuilder cb = session.getCriteriaBuilder();   // getting the criteria builder
            CriteriaQuery<ItemCatalogueResponse> cq = cb.createQuery(ItemCatalogueResponse.class);

            Root<Item> item = cq.from(Item.class);  // creating the root object

            Join<Item, SellerItemMapping> seller = item.join("sellers", JoinType.INNER);    // getting all the seller items data for the item
            Join<SellerItemMapping, Seller> merchant = seller.join("seller", JoinType.INNER);  // getting all the merchant data for the items

            if(request.getCategories() != null && !request.getCategories().isEmpty()){
                predicates.add(item.get("category").in(request.getCategories()));  // adding the category filter
            }

            if(request.getSellerIds() != null && !request.getSellerIds().isEmpty()) {
                predicates.add(merchant.get("sellerId").in(request.getSellerIds()));   // getting all the sellers for the merchant filter
            }

            // creating the min max price filter for all the items
            if(request.getMinPrice()!=null) {
                predicates.add(cb.ge(seller.get("amount"), request.getMinPrice()));   // getting the greater than equal to pricing filter for the items
            }

            if(request.getMaxPrice()!=null) {
                predicates.add(cb.le(seller.get("amount"), request.getMaxPrice()));  // getting the lesser than equal to pricing filter for the items
            }

            // getting the item search by the item name filter
            if(request.getSearchText() != null && !request.getSearchText().isBlank()) {
                predicates.add(cb.like(cb.lower(item.get("itemName")), "%" + request.getSearchText().toLowerCase() + "%"));
            }

            // keeping only the cheaper item rows for all the merchants associated with the items
            Subquery<Double> sub = cq.subquery(Double.class);
            Root<SellerItemMapping> subRoot = sub.from(SellerItemMapping.class);
            sub.select(cb.min(subRoot.get("amount")));
            sub.where(cb.equal(subRoot.get("item"), item));

            predicates.add(cb.equal(seller.get("amount"), sub));

            // building the response from the query results
            cq.select(cb.construct(ItemResponse.class,
                    item.get("id"), item.get("itemId"), item.get("itemName"), item.get("category"), merchant.get("id"), merchant.get("sellerId"), merchant.get("sellerName"),
                    seller.get("amount"), seller.get("availableQuantity")));

            cq.where(predicates.toArray(new Predicate[0]));

            if("PRICE_ASC".equals(request.getSortBy())) {
                cq.orderBy(cb.asc(seller.get("amount")));   // sorting by the cost of the item
            }
            else if("PRICE_DESC".equals(request.getSortBy())) {
                cq.orderBy(cb.desc(seller.get("amount")));  // sorting by the descending cost of the item
            }
            else {
                cq.orderBy(cb.asc(item.get("itemName")));  // sorting by the name of the item
            }

            // implementing pagination
            TypedQuery<ItemCatalogueResponse> query = session.createQuery(cq);
            query.setFirstResult(request.getPage() * request.getSize());
            query.setMaxResults(request.getSize());

            List<ItemCatalogueResponse> data = query.getResultList();

            // getting the count queries for all the related filter implementations
            CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);  // creating the count query for getting the total number of records
            Root<Item> countRoot = countQuery.from(Item.class);   // creating the root object for the count query

            Join<Item, SellerItemMapping> countSeller = countRoot.join("sellers", JoinType.INNER);  // getting all the seller item mappings associated with the item
            Join<SellerItemMapping, Seller> countMerchant = countSeller.join("seller", JoinType.INNER);  // getting all the seller details associated with the seller item mappings

            List<Predicate> countPredicates = new ArrayList<>();   // storing all the conditional predicates for the count query

            // adding the category filter for the count query
            if(request.getCategories() != null && !request.getCategories().isEmpty()) {
                countPredicates.add(countRoot.get("category").in(request.getCategories()));
            }

            // adding the seller filter for the count query
            if(request.getSellerIds() != null && !request.getSellerIds().isEmpty()) {
                countPredicates.add(countMerchant.get("sellerId").in(request.getSellerIds()));
            }

            // adding the minimum price filter for the count query
            if(request.getMinPrice()!=null) {
                countPredicates.add(cb.ge(countSeller.get("amount"), request.getMinPrice()));
            }

            // adding the maximum price filter for the count query
            if(request.getMaxPrice()!=null) {
                countPredicates.add(cb.le(countSeller.get("amount"), request.getMaxPrice()));
            }

            // adding the item name search filter for the count query
            if(request.getSearchText()!=null && !request.getSearchText().isBlank()) {
                countPredicates.add(cb.like(cb.lower(countRoot.get("itemName")), "%" + request.getSearchText().toLowerCase() + "%"));
            }

            // creating the subquery to get the lowest priced seller for each item
            Subquery<Double> countSub = countQuery.subquery(Double.class);
            Root<SellerItemMapping> countSubRoot = countSub.from(SellerItemMapping.class); // creating the root object for the seller item mapping table

            countSub.select(cb.min(countSubRoot.get("amount"))); // getting the minimum price for each item
            countSub.where(cb.equal(countSubRoot.get("item"), countRoot)); // matching the item from the seller item mapping table with the item table
            countPredicates.add(cb.equal(countSeller.get("amount"), countSub)); // adding the cheapest seller predicate to the count query

            countQuery.select(cb.countDistinct(countRoot)); // getting the total distinct item count after applying all filters
            countQuery.where(countPredicates.toArray(new Predicate[0])); // applying all the predicates to the count query
            Long totalCount = session.createQuery(countQuery).getSingleResult(); // executing the count query to get the total number of records

            // returning the paginated response along with the total count
            return new PageImpl<>(data, PageRequest.of(request.getPage(), request.getSize()), totalCount);

        } catch (Exception e) {
            logger.info("Error during item search: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }*/
}
