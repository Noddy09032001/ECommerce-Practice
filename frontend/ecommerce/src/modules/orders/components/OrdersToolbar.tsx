/*"use client";

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
}*/

"use client";

import OrdersFilters from "./OrdersFilters";
import OrdersTabs from "./OrdersTabs";

interface Props {
  activeTab: string;
  setActiveTab: (tab: string) => void;

  filters: {
    searchText: string;
    fromDate: string;
    toDate: string;
    minAmount: string;
    maxAmount: string;
  };

  setFilters: React.Dispatch<
    React.SetStateAction<{
      searchText: string;
      fromDate: string;
      toDate: string;
      minAmount: string;
      maxAmount: string;
    }>
  >;
}

export default function OrdersToolbar({
  activeTab,
  setActiveTab,
  filters,
  setFilters,
}: Props) {
  return (
    <div className="mt-8 bg-card border border-default rounded-2xl p-5 flex flex-col xl:flex-row xl:justify-between xl:items-center gap-5">
      <OrdersTabs active={activeTab} setActive={setActiveTab} />
      <OrdersFilters filters={filters} setFilters={setFilters} />
    </div>
  );
}