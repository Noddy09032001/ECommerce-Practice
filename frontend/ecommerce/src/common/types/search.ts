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

export interface OrderSearchRequest {
  userName?: string;   // the username of the user
  email?: string;  // the email of the user

  searchText?: string;  // the search text for the order containing the order id

  minOrderAmount?: number;  // the min order amount
  maxOrderAmount?: number;  // the max order amount

  fromDate?: string;  // from date filter for order
  toDate?: string;   // to date filter for the order

  currentStatus?: string;  // current status of the order

  sortBy?: string;  // filters for order sorting

  page: number;   // the current page number for pagination
  size: number;  // the number of items per page 
}

export interface PageResponse<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}

export interface OrderSearchResponse {
    orderId: string;
    currentStatus: string;
    grandTotal: number;
    createdOn: string;
    orderDetails: {
        paymentMode: string;
        address: string;
        city: string;
        state: string;
    };
}