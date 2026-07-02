export interface CartItem {
    itemId: string;
    itemName: string;
    sellerId: string;
    sellerName: string;
    quantity: number;
    amount: number;
    transportationCharges: number;
    otherCharges: number;
    totalCost: number;
}