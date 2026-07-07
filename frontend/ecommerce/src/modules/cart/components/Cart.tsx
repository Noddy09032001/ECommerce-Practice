/*"use client";
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

export default Cart;*/

"use client";

import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { useCart } from "@/src/common/context/CartContext";
import { useRouter } from "next/navigation";

const Cart = () => {
  const router = useRouter();

  const { items, removeItem } = useCart();

  const total = items.reduce(
    (sum, item) => sum + item.totalCost * item.quantity,
    0,
  );

  return (
    <CheckoutLayout currentStep="cart">
      <div>
        {/* heading */}

        <div className="mb-10">
          <h1 className="text-3xl font-bold text-foreground">Shopping Cart</h1>

          <p className="mt-2 text-secondary">
            Review your items before checkout
          </p>
        </div>

        {/* empty state */}

        {items.length === 0 && (
          <div className="py-20 text-center">
            <h2 className="text-xl font-semibold text-foreground">
              Your cart is empty
            </h2>

            <p className="mt-3 text-secondary">Add some products to continue</p>
          </div>
        )}

        {/* items */}

        <div className="space-y-6">
          {items.map((item) => (
            <div
              key={`${item.itemId}-${item.sellerId}`}
              className="flex items-center justify-between border border-default rounded-2xl bg-card p-6 shadow-theme transition-all"
            >
              {/* left */}

              <div className="flex items-center gap-5">
                {/* image placeholder */}

                <div className="h-24 w-24 rounded-xl bg-secondary-bg flex items-center justify-center">
                  <span className="text-secondary text-sm">Image</span>
                </div>

                {/* details */}

                <div>
                  <h2 className="text-lg font-semibold text-foreground">
                    {item.itemName}
                  </h2>

                  <p className="mt-1 text-secondary">
                    Sold by {item.sellerName}
                  </p>

                  <p className="mt-1 text-secondary">
                    Quantity: {item.quantity}
                  </p>

                  <button
                    onClick={() => removeItem(item.itemId, item.sellerId)}
                    className="mt-3 text-danger text-sm font-medium hover:opacity-80 transition"
                  >
                    Remove
                  </button>
                </div>
              </div>

              {/* right */}

              <div className="text-right">
                <p className="text-sm text-secondary">Item Total</p>

                <h3 className="mt-1 text-2xl font-bold text-foreground">
                  ₹{item.totalCost.toLocaleString()}
                </h3>

                <p className="mt-2 text-xs text-secondary">
                  incl. taxes & shipping
                </p>
              </div>
            </div>
          ))}
        </div>

        {/* footer */}

        {items.length > 0 && (
          <div className="mt-12 flex justify-between items-center border-t border-default pt-8">
            <div>
              <p className="text-secondary">Grand Total</p>

              <h2 className="text-3xl font-bold text-foreground">
                ₹{total.toLocaleString()}
              </h2>
            </div>

            <button
              onClick={() => router.push("/checkout/address")}
              className="px-8 py-4 rounded-xl bg-primary text-white font-semibold hover:bg-primary-hover transition-all shadow-theme"
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
