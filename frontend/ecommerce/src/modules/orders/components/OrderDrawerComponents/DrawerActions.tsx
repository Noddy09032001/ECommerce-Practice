"use client";

import { Download, MapPinned, Ban } from "lucide-react";

export default function DrawerActions() {
  return (
    <section>
      <div className="flex items-center gap-3 mb-6">
        <h2 className="text-2xl font-semibold text-foreground">Actions</h2>
      </div>

      <div className="grid md:grid-cols-3 gap-5">
        <button
          className="
            flex
            items-center
            justify-center
            gap-3
            h-14
            rounded-xl
            bg-primary
            text-white
            font-semibold
            hover:opacity-90
            transition
          "
        >
          <Download size={18} />
          Download Invoice
        </button>

        <button
          className="
            flex
            items-center
            justify-center
            gap-3
            h-14
            rounded-xl
            border
            border-default
            bg-card
            hover:bg-secondary-bg
            transition
          "
        >
          <MapPinned size={18} />
          Track Shipment
        </button>

        <button
          className="
            flex
            items-center
            justify-center
            gap-3
            h-14
            rounded-xl
            border
            border-red-500
            text-red-500
            hover:bg-red-500
            hover:text-white
            transition
          "
        >
          <Ban size={18} />
          Cancel Order
        </button>
      </div>
    </section>
  );
}
