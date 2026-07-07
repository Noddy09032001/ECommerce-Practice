/*"use client";
import { useState } from "react";

const tabs = ["ALL", "PENDING", "DELIVERED", "CANCELLED"];

export default function OrdersToolbar() {
  const [selectedTab, setSelectedTab] = useState("ALL");

  return (
    <div className="mt-8 bg-card border border-default rounded-2xl p-5 transition-colors">
      <div className="flex flex-col xl:flex-row xl:justify-between xl:items-center gap-6">
        

        <div className="flex flex-wrap gap-3">
          {tabs.map((tab) => (
            <button
              key={tab}
              onClick={() => setSelectedTab(tab)}
              className={`px-5 py-2 rounded-xl font-medium transition-all ${
                selectedTab === tab
                  ? "bg-primary text-white"
                  : "bg-secondary-bg text-secondary hover:text-foreground"
              }`}
            >
              {tab === "ALL"
                ? "All Orders"
                : tab.charAt(0) + tab.slice(1).toLowerCase()}
            </button>
          ))}
        </div>

        <div className="flex flex-wrap gap-4">
          <div className="flex items-center gap-3">
            <input
              type="date"
              className="px-4
                py-2
                rounded-xl
                bg-card
                border
                border-default
                text-foreground
                outline-none
              "
            />

            <span className="text-secondary">to</span>

            <input
              type="date"
              className="
                px-4
                py-2
                rounded-xl
                bg-card
                border
                border-default
                text-foreground
                outline-none
              "
            />
          </div>

          

          <div className="flex items-center gap-3">
            <input
              type="number"
              placeholder="Min"
              className="
                w-28
                px-4
                py-2
                rounded-xl
                bg-card
                border
                border-default
                text-foreground
                placeholder:text-secondary
                outline-none
              "
            />

            <span className="text-secondary">-</span>

            <input
              type="number"
              placeholder="Max"
              className="
                w-28
                px-4
                py-2
                rounded-xl
                bg-card
                border
                border-default
                text-foreground
                placeholder:text-secondary
                outline-none
              "
            />
          </div>
        </div>
      </div>
    </div>
  );
}
*/

"use client";

import OrdersFilters from "./OrdersFilters";
import OrdersTabs from "./OrdersTabs";

interface Props {
  activeTab: string;
  setActiveTab: (tab: string) => void;
}

export default function OrdersToolbar({ activeTab, setActiveTab }: Props) {
  return (
    <div className="mt-8 bg-card border border-default rounded-2xl p-5 flex justify-between items-center">
      <OrdersTabs active={activeTab} setActive={setActiveTab} />
      <OrdersFilters />
    </div>
  );
}