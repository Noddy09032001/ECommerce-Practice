package com.code.ecommerce.repository;

import com.code.ecommerce.dto.requests.ItemSearchRequest;
import com.code.ecommerce.dto.response.ItemCatalogueResponse;
import org.springframework.data.domain.Page;

public interface ItemSearchRepository {
    Page<ItemCatalogueResponse> searchItems(ItemSearchRequest request);  // searching the items based on the request
}
