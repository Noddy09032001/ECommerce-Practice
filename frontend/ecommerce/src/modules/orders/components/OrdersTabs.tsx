"use client";

const tabs = ["All Orders", "Pending", "Delivered", "Cancelled"];

interface Props {
  active: string;
  setActive: (tab: string) => void;
}

export default function OrdersTabs({ active, setActive }: Props) {
  return (
    <div className="flex gap-3">
      {tabs.map((tab) => (
        <button
          key={tab}
          onClick={() => setActive(tab)}
          className={`
            px-5
            py-2
            rounded-xl
            transition-colors
            font-medium
            ${
              active === tab
                ? "bg-primary text-white"
                : "bg-secondary-bg text-secondary"
            }
          `}
        >
          {tab}
        </button>
      ))}
    </div>
  );
}
