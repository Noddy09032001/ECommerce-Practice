package com.code.ecommerce.service.search;

import com.code.ecommerce.dto.requests.ItemSearchRequest;
import com.code.ecommerce.dto.response.ItemCatalogueResponse;
import com.code.ecommerce.repository.ItemRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImplementation implements SearchService{
    private static final Logger logger = LoggerFactory.getLogger(SearchServiceImplementation.class);  // getting the logger

    private final ItemRepository itemRepository;

    @Autowired
    public SearchServiceImplementation(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    /**
     * Searches the item catalogue.
     *
     * @param request search request containing the search parameters
     * @return paginated catalogue response for the given search parameters
     */
    @Override
    public Page<ItemCatalogueResponse> searchItems(ItemSearchRequest request) {
        logger.info("Inside the search items service method - ");
        return itemRepository.searchItems(request);
    }
}
