"use client";

import {useCart} from "@/src/common/context/CartContext";

export default function OrderSummary(){

    const {items} = useCart();
    const subtotal = items.reduce((a,b) => a + (b.amount * b.quantity),0);
    const transport = items.reduce((a,b) => a + (b.transportationCharges * b.quantity),0);
    const others = items.reduce((a,b) => a + (b.otherCharges * b.quantity),0);
    const total = items.reduce((a,b) => a + (b.totalCost * b.quantity),0);

    return(
        <div className="sticky top-36">
            <div className="bg-white dark:bg-zinc-900 rounded-xl border border-zinc-200 dark:border-zinc-800 p-6 transition-colors">
                <h2 className="text-xl font-bold text-black dark:text-white">
                    Payment Summary
                </h2>

                <div className="mt-6 space-y-4">
                    <div className="flex justify-between text-zinc-700 dark:text-zinc-300">
                        <span>
                            Subtotal
                        </span>

                        <span>
                            ₹{subtotal}
                        </span>
                    </div>

                    <div className="flex justify-between text-zinc-700 dark:text-zinc-300">
                        <span>
                            Transport
                        </span>

                        <span>
                            ₹{transport}
                        </span>
                    </div>

                    <div className="flex justify-between text-zinc-700 dark:text-zinc-300">
                        <span>
                            Other Charges
                        </span>

                        <span>
                            ₹{others}
                        </span>
                    </div>

                    <hr className="border-zinc-300 dark:border-zinc-700"/>

                    <div className="flex justify-between text-xl font-bold text-black dark:text-white">
                        <span>
                            Grand Total
                        </span>

                        <span>
                            ₹{total}
                        </span>
                    </div>
                </div>
            </div>
        </div>
    );
}