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
        <h1 className="text-3xl font-bold text-foreground">Payment Method</h1>

        <p className="mt-2 text-muted">Select a payment mode</p>

        <div className="mt-8 space-y-4">
          <div
            onClick={() => setPaymentMode("CARD")}
            className={`p-5 rounded-xl border border-default cursor-pointer transition-colors bg-card text-foreground ${
              paymentMode === "CARD"
                ? "ring-2 ring-foreground"
                : "hover:border-foreground/40"
            }`}
          >
            Credit/Debit Card
          </div>

          <div
            onClick={() => setPaymentMode("ONLINE")}
            className={`p-5 rounded-xl border border-default cursor-pointer transition-colors bg-card text-foreground ${
              paymentMode === "ONLINE"
                ? "ring-2 ring-foreground"
                : "hover:border-foreground/40"
            }`}
          >
            UPI / Net Banking
          </div>

          <div
            onClick={() => setPaymentMode("CASH")}
            className={`p-5 rounded-xl border border-default cursor-pointer transition-colors bg-card text-foreground ${
              paymentMode === "CASH"
                ? "ring-2 ring-foreground"
                : "hover:border-foreground/40"
            }`}
          >
            Cash On Delivery
          </div>
        </div>

        {paymentMode === "CARD" && (
          <div className="mt-8 space-y-4">
            <input
              placeholder="Card Number"
              className="w-full p-4 rounded-xl bg-card border border-default text-foreground placeholder:text-muted outline-none focus:ring-2 focus:ring-foreground/20"
            />

            <div className="grid grid-cols-2 gap-4">
              <input
                placeholder="MM/YY"
                className="p-4 rounded-xl bg-card border border-default text-foreground placeholder:text-muted outline-none focus:ring-2 focus:ring-foreground/20"
              />

              <input
                placeholder="CVV"
                className="p-4 rounded-xl bg-card border border-default text-foreground placeholder:text-muted outline-none focus:ring-2 focus:ring-foreground/20"
              />
            </div>
          </div>
        )}

        <div className="mt-10 flex justify-end">
          <button
            onClick={() => router.push("/checkout/confirm")}
            className="px-8 py-3 bg-foreground text-background rounded-xl font-semibold transition-all hover:opacity-90 cursor-pointer"
          >
            Proceed To Payment
          </button>
        </div>
      </div>
    </CheckoutLayout>
  );
};

export default Checkout;
