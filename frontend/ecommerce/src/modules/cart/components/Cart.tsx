'use client'
import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { useCart } from "@/src/common/context/CartContext";
import { useRouter } from "next/navigation";

const Cart = () => {

    const router = useRouter()  // using the router for navigations
    const {items, removeItem} = useCart()  // using the cart function for fetching the items
    const total = items.reduce((sum, item) => sum + item.totalCost * item.quantity, 0) // getting the totals

    return(
        <CheckoutLayout currentStep="cart">
            <div className="min-h-screen bg-white dark:bg-black text-black dark:text-white p-8 transition-colors">
                <h1 className="text-4xl font-bold">Shopping Cart</h1>

                {items.map(item => (
                    <div
                        key={`${item.itemId}-${item.sellerId}`}
                        className="mt-4 bg-zinc-100 dark:bg-zinc-900 border border-zinc-200 dark:border-zinc-800 p-5 rounded-xl">

                        <h2 className="text-xl font-semibold">
                            {item.itemName}
                        </h2>

                        <p className="text-zinc-700 dark:text-zinc-300">
                            Seller:{" "}
                            {item.sellerName}
                        </p>

                        <p className="text-zinc-700 dark:text-zinc-300">
                            Qty:{" "}
                            {item.quantity}
                        </p>

                        <p className="font-semibold">
                            ₹{item.totalCost}
                        </p>

                        <button
                            className="mt-2 text-red-600 dark:text-red-400 transition cursor-pointer"
                            onClick={() => removeItem(item.itemId, item.sellerId)}>
                            Remove
                        </button>
                    </div>
                ))}

                {items.length > 0 && (
                    <div className="mt-8 border-t border-zinc-200 dark:border-zinc-800 pt-6">
                        <h2 className="text-2xl font-bold">
                            Total: ₹{total}
                        </h2>

                        <button
                            onClick={() => router.push("/checkout/address")}
                            className="mt-6 px-6 py-3 bg-black dark:bg-white text-white dark:text-black rounded-xl font-semibold transition cursor-pointer">
                            Proceed To Checkout
                        </button>
                    </div>
                )}
            </div>
        </CheckoutLayout>
    )
}

export default Cart;