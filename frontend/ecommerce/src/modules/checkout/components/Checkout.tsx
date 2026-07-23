"use client";
import { createPayment } from "@/src/common/api/paymentApiService";
import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { PaymentItem, PaymentRequest } from "@/src/common/types/payments";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";

const Checkout = () => {
  const router = useRouter();
  const [paymentMode, setPaymentMode] = useState("CARD");

  const [items, setItems] = useState<PaymentRequest[]>([]);
  useEffect(() => {
    const currentItems = sessionStorage.getItem("currentOrder"); // getting the items from the session storage
    if (currentItems) {
      const parsedItems = JSON.parse(currentItems); // parsing the items in the session storage 
      setItems(parsedItems);
      console.log("Items from session storage:", parsedItems);
    }
  }, []);

  // function to handle the stripe payment 
  const handleStripePayment = async () => {
    try {
      
      if (paymentMode === "CARD") {
        const requestBody = items; // getting the request body
        console.log("The request body: ", requestBody);
        const response = await createPayment(requestBody); // calling the orders api
        window.location.href = response;
        return;

      }

      router.push("/checkout/payment"); // routing to the payment page after the response

    } catch (error) {
      alert("Unable to initiate payment.");
      console.log(error);
    }
  };

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
          <div className="mt-8 rounded-xl border border-default bg-card p-6">
            <p className="text-foreground font-medium">
              You will be redirected securely to Stripe Checkout to complete your payment.
            </p>

            <p className="mt-2 text-muted">
              Credit Cards, Debit Cards and supported payment methods will be available there.
            </p>
          </div>
        )}

        <div className="mt-10 flex justify-end">
          <button
            onClick={handleStripePayment}
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
