export interface CartItem {
    itemId: number;   // the id for the current item
    itemName: string;  // the name of the current item
    sellerId: number;  // the id of the current merchant associated with the item
    sellerName: string;  // the name of the merchant
    quantity: number;  // the order quantity
    amount: number;  // the amount without taxes of the order
    transportationCharges: number;  // the transportation charges cost
    otherCharges: number;  // the cost of other charges
    totalCost: number;  // the total cost of the item with charges
}