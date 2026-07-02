"use client";
import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { useRouter } from "next/navigation";
import { useState } from "react";

const Checkout = () => {
  const router = useRouter();
  const [paymentMode, setPaymentMode] = useState("CARD");

  return (
    <CheckoutLayout currentStep="payment">
      <div>
        <h1 className="text-3xl font-bold text-black dark:text-white">
          Payment Method
        </h1>

        <p className="mt-2 text-zinc-600 dark:text-zinc-400">
          Select a payment mode
        </p>

        <div className="mt-8 space-y-4">
          <div
            onClick={() => setPaymentMode("CARD")}
            className={`p-5 rounded-xl border cursor-pointer transition text-black dark:text-white bg-zinc-100 dark:bg-zinc-800 ${
              paymentMode === "CARD"
                ? "border-black dark:border-white"
                : "border-zinc-300 dark:border-zinc-700"
            }`}>
            Credit/Debit Card
          </div>

          <div
            onClick={() => setPaymentMode("ONLINE")}
            className={`p-5 rounded-xl border cursor-pointer transition text-black dark:text-white bg-zinc-100 dark:bg-zinc-800 ${
              paymentMode === "ONLINE"
                ? "border-black dark:border-white"
                : "border-zinc-300 dark:border-zinc-700"
            }`}>
            UPI / Net Banking
          </div>

          <div
            onClick={() => setPaymentMode("CASH")}
            className={`p-5 rounded-xl border cursor-pointer transition text-black dark:text-white bg-zinc-100 dark:bg-zinc-800 ${
              paymentMode === "CASH"
                ? "border-black dark:border-white"
                : "border-zinc-300 dark:border-zinc-700"
            }`}>
            Cash On Delivery
          </div>
        </div>

        {paymentMode === "CARD" && (
          <div className="mt-8 space-y-4">
            <input
              placeholder="Card Number"
              className="w-full p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-300 dark:border-zinc-700 text-black dark:text-white outline-none"/>

            <div className="grid grid-cols-2 gap-4">
              <input
                placeholder="MM/YY"
                className="p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-300 dark:border-zinc-700 text-black dark:text-white outline-none"/>

              <input
                placeholder="CVV"
                className="p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-300 dark:border-zinc-700 text-black dark:text-white outline-none"/>
            </div>
          </div>
        )}

        <div className="mt-10 flex justify-end">
          <button
            onClick={() => router.push("/checkout/confirm")}
            className="px-8 py-3 bg-black dark:bg-white text-white dark:text-black rounded-xl font-semibold transition cursor-pointer">
            Place Order
          </button>
        </div>
      </div>
    </CheckoutLayout>
  );
};

export default Checkout;