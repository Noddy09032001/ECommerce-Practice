/*"use client";

export default function OrdersFilters() {
  return (
    <div className="flex gap-3">
      <input type="date"
        className="px-4 py-2 rounded-xl bg-card border border-default"
      />

      <input
        type="date"
        className="px-4 py-2 rounded-xl bg-card border border-default"
      />

      <input type="number" placeholder="Min ₹"
        className="w-28 px-4 py-2 rounded-xl bg-card border border-default"
      />

      <input type="number" placeholder="Max ₹"
        className="w-28 px-4 py-2 rounded-xl bg-card border border-default"
      />
    </div>
  );
}*/

"use client";

interface Props {
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

export default function OrdersFilters({ filters, setFilters }: Props) {
  return (
    <div className="flex flex-wrap items-center gap-3">
      {/* Search Order */}

      <input
        type="text"
        placeholder="Search Order ID..."
        value={filters.searchText}
        onChange={(e) =>
          setFilters((prev) => ({
            ...prev,
            searchText: e.target.value,
          }))
        }
        className="
          w-56
          px-4
          py-2
          rounded-xl
          bg-card
          border
          border-default
          outline-none
          focus:ring-2
          focus:ring-primary
          transition-colors
        "
      />

      {/* From Date */}

      <input
        type="date"
        value={filters.fromDate}
        onChange={(e) =>
          setFilters((prev) => ({
            ...prev,
            fromDate: e.target.value,
          }))
        }
        className="
          px-4
          py-2
          rounded-xl
          bg-card
          border
          border-default
          outline-none
          focus:ring-2
          focus:ring-primary
        "
      />

      {/* To Date */}

      <input
        type="date"
        value={filters.toDate}
        onChange={(e) =>
          setFilters((prev) => ({
            ...prev,
            toDate: e.target.value,
          }))
        }
        className="
          px-4
          py-2
          rounded-xl
          bg-card
          border
          border-default
          outline-none
          focus:ring-2
          focus:ring-primary
        "
      />

      {/* Min Amount */}

      <input
        type="number"
        placeholder="Min ₹"
        value={filters.minAmount}
        onChange={(e) =>
          setFilters((prev) => ({
            ...prev,
            minAmount: e.target.value,
          }))
        }
        className="
          w-32
          px-4
          py-2
          rounded-xl
          bg-card
          border
          border-default
          outline-none
          focus:ring-2
          focus:ring-primary
        "
      />

      {/* Max Amount */}

      <input
        type="number"
        placeholder="Max ₹"
        value={filters.maxAmount}
        onChange={(e) =>
          setFilters((prev) => ({
            ...prev,
            maxAmount: e.target.value,
          }))
        }
        className="
          w-32
          px-4
          py-2
          rounded-xl
          bg-card
          border
          border-default
          outline-none
          focus:ring-2
          focus:ring-primary
        "
      />
    </div>
  );
}
