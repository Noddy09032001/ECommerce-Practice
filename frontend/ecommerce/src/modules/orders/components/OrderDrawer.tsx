/*"use client";

import { X } from "lucide-react";

interface Props {
  order: any;
  onClose: () => void;
}

export default function OrderDrawer({ order, onClose }: Props) {
  return (
    <>


      <div
        onClick={onClose}
        className="fixed inset-0 bg-black/40 backdrop-blur-sm z-40"
      />



      <div
        className="
          fixed
          top-0
          right-0
          w-[550px]
          h-screen
          bg-card
          border-l
          border-default
          z-50
          shadow-2xl
          flex
          flex-col
        "
      >


        <div className="px-8 py-6 border-b border-default flex justify-between items-center">
          <div>
            <h2 className="text-2xl font-bold text-foreground">
              {order.orderId}
            </h2>

            <p className="text-secondary mt-1">Order Details</p>
          </div>

          <button
            onClick={onClose}
            className="
              h-10
              w-10
              rounded-xl
              hover:bg-secondary-bg
              transition-colors
              flex
              items-center
              justify-center
            "
          >
            <X />
          </button>
        </div>



        <div className="flex-1 overflow-y-auto p-8 space-y-8">
          <div className="grid grid-cols-2 gap-6">
            <div>
              <p className="text-secondary text-sm">Total Amount</p>

              <h3 className="text-xl font-bold mt-2">
                ₹{order.amount.toLocaleString()}
              </h3>
            </div>

            <div>
              <p className="text-secondary text-sm">Payment</p>

              <h3 className="mt-2 font-semibold">{order.payment}</h3>
            </div>

            <div>
              <p className="text-secondary text-sm">Date</p>

              <h3 className="mt-2">{order.date}</h3>
            </div>

            <div>
              <p className="text-secondary text-sm">Status</p>

              <h3 className="mt-2">{order.status}</h3>
            </div>
          </div>

          <div>
            <p className="text-secondary text-sm">Delivery Address</p>

            <div className="mt-3 p-5 rounded-xl bg-secondary-bg">
              {order.address}
            </div>
          </div>

          <div>
            <p className="text-secondary text-sm mb-3">Order Summary</p>

            <div className="rounded-xl bg-secondary-bg p-6 space-y-3">
              <div className="flex justify-between">
                <span className="text-secondary">Items</span>

                <span>3</span>
              </div>

              <div className="flex justify-between">
                <span className="text-secondary">Shipping</span>

                <span>₹500</span>
              </div>

              <div className="border-t border-default pt-3 flex justify-between font-semibold">
                <span>Total</span>

                <span>₹{order.amount.toLocaleString()}</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
*/

"use client";

import { useEffect } from "react";
import DrawerHeader from "./OrderDrawerComponents/DrawerHeader";
import DrawerItems from "./OrderDrawerComponents/DrawerItems";
import DrawerTimeline from "./OrderDrawerComponents/DrawerTimeline";
import DrawerSummary from "./OrderDrawerComponents/DrawerSummary";
import DrawerShipment from "./OrderDrawerComponents/DrawerShipment";
import DrawerActions from "./OrderDrawerComponents/DrawerActions";

interface Props {
  open: boolean;
  onClose: () => void;
  order: any;
}

export default function OrderDrawer({ open, onClose, order }: Props) {
  useEffect(() => {
    const close = (e: KeyboardEvent) => {
      if (e.key === "Escape") {
        onClose();
      }
    };

    window.addEventListener("keydown", close);

    return () => window.removeEventListener("keydown", close);
  }, [onClose]);

  if (!order) return null;

  return (
    <div
      className={`
        fixed inset-0 z-50
        transition-all duration-300
        ${open ? "pointer-events-auto" : "pointer-events-none"}
      `}
    >

      <div
        onClick={onClose}
        className={`
          absolute inset-0
          bg-black/40
          backdrop-blur-sm
          transition-opacity duration-300
          ${open ? "opacity-100" : "opacity-0"}
        `}
      />

      <div
        className={`
          absolute
          top-0
          right-0
          h-full
          w-[80vw]
          max-w-[1400px]
          bg-background
          shadow-2xl
          transition-transform
          duration-300
          ease-out
          ${open ? "translate-x-0" : "translate-x-full"}
        `}
      >
        <DrawerHeader order={order} onClose={onClose} />
        <div className="grid grid-cols-12 h-[calc(100%-76px)]">
          <div className="col-span-8 overflow-y-auto px-10 py-8 space-y-10">
            <DrawerItems order={order} />
            <DrawerTimeline />
            <DrawerShipment />
            <DrawerActions />
          </div>
          <div className="col-span-4 border-l border-default bg-secondary-bg overflow-y-auto">
            <div className="sticky top-0 p-8">
              <DrawerSummary order={order} />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}