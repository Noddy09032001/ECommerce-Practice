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

// Generic paginated response
export interface PageResponse<T> {
  content: T[]; // page content
  totalPages: number; // total pages
  totalElements: number; // total records
  size: number; // page size
  number: number; // current page
}

// Customer information
export interface UserDetailsResponse {
  name: string; // customer name
  email: string; // customer email
  city: string; // customer city
  state: string; // customer state
  country: string; // customer country
  role: string; // customer role
  gender: string; // customer gender
}

// Order information
export interface OrderDetailsResponse {
  orderDetailsId: string; // order details id

  amount: number; // subtotal
  totalTaxInAmount: number; // total tax
  totalOrderAmount: number; // order total

  paymentMode: string; // payment mode

  city: string; // delivery city
  state: string; // delivery state
  address: string; // delivery address

  createdOn: string; // creation time
  modifiedOn: string; // last modification
}

// Individual order item
export interface OrderItemResponse {
  itemId: string; // item id
  itemName: string; // item name
  category: string; // item category

  quantity: number; // ordered quantity

  amountWithoutTax: number; // base amount

  cgstAmount: number; // CGST
  sgstAmount: number; // SGST
  igstAmount: number; // IGST
  vatAmount: number; // VAT
  cessAmount: number; // CESS

  totalAmount: number; // final amount
}

// Seller-wise order
export interface SellerOrderResponse {
  sellerId: string; // seller id
  sellerName: string; // seller name

  amount: number; // subtotal
  totalTaxInAmount: number; // tax amount
  totalOrderAmount: number; // seller total

  city: string; // seller city
  state: string; // seller state
  address: string; // seller address

  items: OrderItemResponse[]; // seller items
}

// Order status history
export interface OrderStatusHistoryResponse {
  status: string; // order status
  remarks: string; // remarks
  createdOn: string; // status time
  createdBy: string; // updated by
}

// Order search response
export interface OrderSearchResponse {
  orderId: string; // order id
  currentStatus: string; // current status

  totalItems: number; // total items
  grandTotal: number; // grand total

  createdOn: string; // creation time
  modifiedOn: string; // last modification

  createdBy: string; // created by
  modifiedBy: string; // modified by

  customer: UserDetailsResponse; // customer details

  orderDetails: OrderDetailsResponse; // order details

  sellerOrders: SellerOrderResponse[]; // seller orders

  orderStatuses: OrderStatusHistoryResponse[]; // status history
}