export interface PaymentItem{
    baseAmount: number; 
    itemId: string;
    itemName: string;
    itemDescription: string;
    otherCharges: number; 
    quantity: number; 
    taxAmount: number; 
    totalAmount: number;
    transportationCharges: number;
}

export interface PaymentRequest{
    orderId: string;
    items: PaymentItem[];
}