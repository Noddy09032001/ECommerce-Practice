"use client";

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
}
