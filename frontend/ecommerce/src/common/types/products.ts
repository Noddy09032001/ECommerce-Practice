// adding the type definition for the seller details
/*export interface Seller {
    sellerId: string;
    sellerName: string;
}

// adding the type definitions for the seller and the item mapping
export interface SellerMapping {
    seller: Seller;
    amount: number;
    transportationCharges: number;
    otherCharges: number;
    totalCost: number;
    availableQuantity: number;
}

// adding the type definition for the images for the product
export interface ProductImage {
    id: string;
    url: string;
}


// adding the type definition for the products for the catalogue page
export interface Product {
    itemId: string;
    itemName: string;
    itemDescription: string;
    sku?: string;
    sellers: SellerMapping[];
    images: ProductImage[]
} */


// Merchant information returned by the backend

export interface Seller {
    sellerId: string;
    sellerName: string;
}

export interface SellerItemResponse {
  name: string;
  amount: number;
  finalAmount: number;
  otherCharges: number;
  transportationCharges: number;
  quantity: number;
}

// Product Image (frontend only)

export interface ProductImage {
  id: string;
  url: string;
}

// Product returned by backend

export interface Product {
  name: string;
  description: string;
  category: string;
  sku: string;
  active: boolean;

  createdOn: string;
  modifiedOn: string;

  cgst: number;
  sgst: number;
  igst: number;
  vat: number;
  cess: number;

  merchants: SellerItemResponse[];

  /**
   * Images are still maintained on the frontend.
   * Remove this field if the backend starts returning images.
   */
  images?: ProductImage[];
}