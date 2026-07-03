"use client";
import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { useCart } from "@/src/common/context/CartContext";
import { useRouter } from "next/navigation";

const Cart = () => {
  const router = useRouter(); // using the router for navigations
  const { items, removeItem } = useCart(); // using the cart function for fetching the items
  const total = items.reduce(
    (sum, item) => sum + item.totalCost * item.quantity,
    0,
  ); // getting the totals

  return (
    <CheckoutLayout currentStep="cart">
      <div className="min-h-screen bg-background text-foreground p-8 transition-colors">
        <h1 className="text-4xl font-bold">Shopping Cart</h1>

        {items.map((item) => (
          <div
            key={`${item.itemId}-${item.sellerId}`}
            className="mt-4 bg-card border border-default p-5 rounded-xl transition-colors"
          >
            <h2 className="text-xl font-semibold text-foreground">
              {item.itemName}
            </h2>

            <p className="text-secondary">Seller: {item.sellerName}</p>

            <p className="text-secondary">Qty: {item.quantity}</p>

            <p className="font-semibold text-foreground">₹{item.totalCost}</p>

            <button
              className="mt-2 text-red-600 dark:text-red-400 transition cursor-pointer"
              onClick={() => removeItem(item.itemId, item.sellerId)}
            >
              Remove
            </button>
          </div>
        ))}

        {items.length > 0 && (
          <div className="mt-8 border-t border-default pt-6">
            <h2 className="text-2xl font-bold text-foreground">
              Total: ₹{total}
            </h2>

            <button
              onClick={() => router.push("/checkout/address")}
              className="mt-6 px-6 py-3 bg-primary text-primary-foreground rounded-xl font-semibold transition cursor-pointer"
            >
              Proceed To Checkout
            </button>
          </div>
        )}
      </div>
    </CheckoutLayout>
  );
};

export default Cart;
