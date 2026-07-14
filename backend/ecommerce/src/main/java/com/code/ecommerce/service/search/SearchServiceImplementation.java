package com.code.ecommerce.service.search;

import com.code.ecommerce.dto.requests.itemSearch.ItemSearchRequest;
import com.code.ecommerce.dto.requests.orderSearch.OrderSearchRequest;
import com.code.ecommerce.dto.response.ItemResponse;
import com.code.ecommerce.dto.response.itemSearch.ItemCatalogueResponse;
import com.code.ecommerce.dto.response.orderSearch.OrderSearchResponse;
import com.code.ecommerce.repository.ItemRepository;

import com.code.ecommerce.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImplementation implements SearchService{
    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImplementation.class);  // getting the logger

    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public SearchServiceImplementation(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Searches the item catalogue.
     *
     * @param request search request containing the search parameters
     * @return paginated catalogue response for the given search parameters
     */
    @Override
    public Page<ItemResponse> searchItems(ItemSearchRequest request) {
        logger.info("Inside the search items service method - ");
        return itemRepository.searchItems(request);
    }

    @Override
    public Page<OrderSearchResponse> searchOrder(OrderSearchRequest request) {
        logger.info("Inside the search orders service method - ");
        return orderRepository.searchOrder(request);
    }
}
