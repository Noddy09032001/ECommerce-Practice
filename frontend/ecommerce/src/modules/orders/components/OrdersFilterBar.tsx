"use client";

interface Props {
  activeTab: string;
  setActiveTab: (tab: string) => void;

  filters: {
    fromDate: string;
    toDate: string;
    minAmount: string;
    maxAmount: string;
  };

  setFilters: (filters: any) => void;
}

const tabs = ["all", "pending", "delivered", "cancelled"];

export default function OrdersFilterBar({
  activeTab,
  setActiveTab,
  filters,
  setFilters,
}: Props) {
  return (
    <div className="bg-card border border-default rounded-2xl p-5">
      <div className="flex flex-col xl:flex-row xl:justify-between gap-6">
        {/* tabs */}

        <div className="flex gap-3 flex-wrap">
          {tabs.map((tab) => (
            <button
              key={tab}
              onClick={() => setActiveTab(tab)}
              className={`px-5 py-2 rounded-xl font-medium transition-all ${
                activeTab === tab
                  ? "bg-primary text-white"
                  : "bg-secondary-bg text-secondary hover:bg-border"
              }`}
            >
              {tab.charAt(0).toUpperCase() + tab.slice(1)}
            </button>
          ))}
        </div>

        {/* filters */}

        <div className="flex flex-wrap gap-3">
          <input
            type="date"
            value={filters.fromDate}
            onChange={(e) =>
              setFilters({
                ...filters,
                fromDate: e.target.value,
              })
            }
            className="px-4 py-2 rounded-xl bg-card border border-default"
          />

          <input
            type="date"
            value={filters.toDate}
            onChange={(e) =>
              setFilters({
                ...filters,
                toDate: e.target.value,
              })
            }
            className="px-4 py-2 rounded-xl bg-card border border-default"
          />

          <input
            type="number"
            placeholder="Min ₹"
            value={filters.minAmount}
            onChange={(e) =>
              setFilters({
                ...filters,
                minAmount: e.target.value,
              })
            }
            className="w-32 px-4 py-2 rounded-xl bg-card border border-default"
          />

          <input
            type="number"
            placeholder="Max ₹"
            value={filters.maxAmount}
            onChange={(e) =>
              setFilters({
                ...filters,
                maxAmount: e.target.value,
              })
            }
            className="w-32 px-4 py-2 rounded-xl bg-card border border-default"
          />
        </div>
      </div>
    </div>
  );
}
