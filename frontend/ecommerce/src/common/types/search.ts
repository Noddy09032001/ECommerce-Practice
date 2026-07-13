export interface ItemSearchRequest {
    categories: string[];  // categories of the item
    sellerIds: string[];  // the id of the sellers

    minPrice?: number;  // the min price of the item
    maxPrice?: number;  // the max price of the item

    searchText?: string;  // the text in the search bar

    sortBy?: "PRICE_ASC" | "PRICE_DESC" | "NAME";  // sorting filter criteria

    page: number;  // the current page number for pagination
    size: number;  // the number of items per page 
}