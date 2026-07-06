package com.code.ecommerce.service.search;

import com.code.ecommerce.dto.requests.itemSearch.ItemSearchRequest;
import com.code.ecommerce.dto.requests.orderSearch.OrderSearchRequest;
import com.code.ecommerce.dto.response.itemSearch.ItemCatalogueResponse;
import com.code.ecommerce.dto.response.orderSearch.OrderSearchResponse;
import org.springframework.data.domain.Page;

public interface SearchService {
    Page<ItemCatalogueResponse> searchItems(ItemSearchRequest request);  // searching the items based on the request parameters
    Page<OrderSearchResponse> searchOrder(OrderSearchRequest request);   // searching the order based on the request parameters
}
