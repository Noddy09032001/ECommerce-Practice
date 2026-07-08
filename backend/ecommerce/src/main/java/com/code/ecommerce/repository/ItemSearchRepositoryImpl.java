package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.itemSearch.ItemSearchRequest;
import com.code.ecommerce.dto.response.itemSearch.ItemCatalogueResponse;
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

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemSearchRepositoryImplementation implements ItemSearchRepository{

    @PersistenceContext
    private EntityManager entityManager;

    private static final Logger logger = LoggerFactory.getLogger(ItemSearchRepositoryImplementation.class);  // getting the logger

    /**
     * Returns the item catalogue based on the search parameters
     *
     * @param request the request containing the details of the search parameters
     * @return the item catalogue based on the search filters
     */
    @Override
    public Page<ItemCatalogueResponse> searchItems(ItemSearchRequest request) {
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
            cq.select(cb.construct(ItemCatalogueResponse.class,
                    item.get("id"), item.get("itemId"), item.get("itemName"), item.get("category"), merchant.get("sellerId"), merchant.get("sellerName"),
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
    }
}
