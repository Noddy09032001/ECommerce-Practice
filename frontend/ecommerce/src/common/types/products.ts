// adding the type definition for the seller details
export interface Seller {
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
}