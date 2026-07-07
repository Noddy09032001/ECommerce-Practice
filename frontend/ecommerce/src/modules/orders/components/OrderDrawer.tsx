"use client";

import { X } from "lucide-react";

interface Props {
  order: any;
  onClose: () => void;
}

export default function OrderDrawer({ order, onClose }: Props) {
  return (
    <>
      {/* backdrop */}

      <div
        onClick={onClose}
        className="fixed inset-0 bg-black/40 backdrop-blur-sm z-40"
      />

      {/* drawer */}

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
        {/* header */}

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

        {/* content */}

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
