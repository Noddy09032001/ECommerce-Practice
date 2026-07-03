"use client";
import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { useRouter } from "next/navigation";
import { useState } from "react";

const Address = () => {
  const router = useRouter();

  const [address, setAddress] = useState({
    street: "",
    city: "",
    state: "",
    country: "",
    pinCode: "",
  });

  return (
    <CheckoutLayout currentStep="address">
      <div>
        <h1 className="text-3xl font-bold text-foreground">Delivery Address</h1>

        <p className="mt-2 text-muted">
          Enter the delivery address for your order
        </p>

        <div className="mt-8 space-y-5">
          <input
            value={address.street}
            onChange={(e) =>
              setAddress({
                ...address,
                street: e.target.value,
              })
            }
            placeholder="Street Address"
            className="w-full p-4 rounded-xl bg-card border border-default text-foreground outline-none transition-colors"
          />

          <div className="grid grid-cols-2 gap-4">
            <input
              value={address.city}
              onChange={(e) =>
                setAddress({
                  ...address,
                  city: e.target.value,
                })
              }
              placeholder="City"
              className="p-4 rounded-xl bg-card border border-default text-foreground outline-none transition-colors"
            />

            <input
              value={address.state}
              onChange={(e) =>
                setAddress({
                  ...address,
                  state: e.target.value,
                })
              }
              placeholder="State"
              className="p-4 rounded-xl bg-card border border-default text-foreground outline-none transition-colors"
            />
          </div>

          <div className="grid grid-cols-2 gap-4">
            <input
              value={address.country}
              onChange={(e) =>
                setAddress({
                  ...address,
                  country: e.target.value,
                })
              }
              placeholder="Country"
              className="p-4 rounded-xl bg-card border border-default text-foreground outline-none transition-colors"
            />

            <input
              value={address.pinCode}
              onChange={(e) =>
                setAddress({
                  ...address,
                  pinCode: e.target.value,
                })
              }
              placeholder="Pin Code"
              className="p-4 rounded-xl bg-card border border-default text-foreground outline-none transition-colors"
            />
          </div>
        </div>

        <div className="mt-10 flex justify-end">
          <button
            onClick={() => router.push("/checkout/payment")}
            className="px-8 py-3 bg-primary text-primary-foreground rounded-xl font-semibold transition cursor-pointer hover:opacity-90"
          >
            Continue
          </button>
        </div>
      </div>
    </CheckoutLayout>
  );
};

export default Address;
