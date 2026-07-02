'use client'
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
          <h1 className="text-3xl font-bold text-black dark:text-white">
            Delivery Address
          </h1>

          <p className="mt-2 text-zinc-600 dark:text-zinc-400">
            Enter the delivery address for your order
          </p>

          <div
            className="mt-8 space-y-5">
            <input
              value={address.street}
              onChange={(e) =>
                setAddress({
                  ...address,
                  street: e.target.value,
                })
              }
              placeholder="Street Address"
              className="w-full p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-200 dark:border-zinc-700 text-black dark:text-white outline-none"/>

            <div
              className="grid grid-cols-2 gap-4">
              <input
                value={address.city}
                onChange={(e) =>
                  setAddress({
                    ...address,
                    city: e.target.value,
                  })
                }
                placeholder="City"
                className="p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-200 dark:border-zinc-700 text-black dark:text-white"
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
                className="p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-200 dark:border-zinc-700 text-black dark:text-white"/>
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
                className="p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-200 dark:border-zinc-700 text-black dark:text-white"/>

              <input
                value={address.pinCode}
                onChange={(e) =>
                  setAddress({
                    ...address,
                    pinCode: e.target.value,
                  })
                }
                placeholder="Pin Code"
                className="p-4 rounded-xl bg-zinc-100 dark:bg-zinc-800 border border-zinc-200 dark:border-zinc-700 text-black dark:text-white"/>
            </div>
          </div>

          <div
            className="mt-10 flex justify-end"
          >
            <button
              onClick={() => router.push("/checkout/payment")}
              className="px-8 py-3 bg-black dark:bg-white text-white dark:text-black rounded-xl font-semibold transition cursor-pointer">
              Continue
            </button>
          </div>
        </div>
      </CheckoutLayout>
    );
}

export default Address; // exporting the function for the address page routing