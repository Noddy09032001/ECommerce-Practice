"use client";

import { X } from "lucide-react";

interface Props {
  order: any;
  onClose: () => void;
}

export default function DrawerHeader({ order, onClose }: Props) {
  const getStatusColor = () => {
    switch (order.status) {
      case "Delivered":
        return "bg-green-100 text-green-700";

      case "Pending":
        return "bg-yellow-100 text-yellow-700";

      case "Cancelled":
        return "bg-red-100 text-red-700";

      case "Shipped":
        return "bg-blue-100 text-blue-700";

      default:
        return "bg-secondary-bg text-secondary";
    }
  };

  return (
    <div className="h-[76px] border-b border-default bg-card px-8 flex items-center justify-between">
      {/* Left */}

      <div>
        <div className="flex items-center gap-4">
          <h1 className="text-2xl font-bold text-foreground">Order Details</h1>

          <span
            className={`
              px-3
              py-1
              rounded-full
              text-sm
              font-semibold
              ${getStatusColor()}
            `}
          >
            {order.status}
          </span>
        </div>

        <p className="mt-1 text-secondary">Order ID • {order.orderId}</p>
      </div>

      {/* Right */}

      <button
        onClick={onClose}
        className="
          h-10
          w-10
          rounded-lg
          flex
          items-center
          justify-center
          hover:bg-secondary-bg
          transition-colors
        "
      >
        <X size={22} />
      </button>
    </div>
  );
}
