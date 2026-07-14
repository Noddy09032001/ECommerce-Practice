package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.itemSearch.ItemSearchRequest;
import com.code.ecommerce.dto.response.ItemResponse;
import com.code.ecommerce.dto.response.itemSearch.ItemCatalogueResponse;
import org.springframework.data.domain.Page;

public interface ItemSearchRepository {
    Page<ItemResponse> searchItems(ItemSearchRequest request);  // searching the items based on the request
}
