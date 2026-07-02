export interface Seller {
    sellerId: string;
    sellerName: string;
}

export interface SellerPrice {
    seller: Seller;
    amount: number;
    transportationCharges: number;
    otherCharges: number;
    totalCost: number;
    availableQuantity?: number;
}

export interface Product {
    itemId: string;
    itemName: string;
    itemDescription: string;
    sku?: string;
    sellers: SellerPrice[];
}