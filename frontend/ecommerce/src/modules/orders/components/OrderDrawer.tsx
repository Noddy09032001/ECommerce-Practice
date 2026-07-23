/*"use client";

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
*/

"use client";

import { useEffect } from "react";

import DrawerHeader from "./OrderDrawerComponents/DrawerHeader";
import DrawerItems from "./OrderDrawerComponents/DrawerItems";
import DrawerTimeline from "./OrderDrawerComponents/DrawerTimeline";
import DrawerSummary from "./OrderDrawerComponents/DrawerSummary";
import DrawerShipment from "./OrderDrawerComponents/DrawerShipment";
import DrawerActions from "./OrderDrawerComponents/DrawerActions";

import { OrderSearchResponse } from "@/src/common/types/search";

interface Props {
  open: boolean;
  onClose: () => void;
  order: OrderSearchResponse | null;
}

export default function OrderDrawer({ open, onClose, order }: Props) {
  useEffect(() => {
    const handleKeyDown = (event: KeyboardEvent) => {
      if (event.key === "Escape") {
        onClose();
      }
    };

    window.addEventListener("keydown", handleKeyDown);

    return () => {
      window.removeEventListener("keydown", handleKeyDown);
    };
  }, [onClose]);

  if (!order) {
    return null;
  }

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
            <DrawerTimeline order={order} />
            <DrawerShipment />
            <DrawerActions orderId={order.orderId} />
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
