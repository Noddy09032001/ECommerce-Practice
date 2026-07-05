package com.code.ecommerce.service.search;

import com.code.ecommerce.dto.requests.ItemSearchRequest;
import com.code.ecommerce.dto.response.ItemCatalogueResponse;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<ItemCatalogueResponse> searchItems(ItemSearchRequest request);
}
