/*"use client";

import { CheckCircle2, Clock3, Circle } from "lucide-react";

const timeline = [
  {
    title: "Order Placed",
    date: "12 Jan 2025 • 10:15 AM",
    completed: true,
  },
  {
    title: "Payment Confirmed",
    date: "12 Jan 2025 • 10:18 AM",
    completed: true,
  },
  {
    title: "Seller Accepted",
    date: "12 Jan 2025 • 11:45 AM",
    completed: true,
  },
  {
    title: "Packed",
    date: "13 Jan 2025 • 09:30 AM",
    completed: true,
  },
  {
    title: "Shipped",
    date: "13 Jan 2025 • 04:10 PM",
    completed: true,
  },
  {
    title: "Out For Delivery",
    date: "14 Jan 2025 • 08:00 AM",
    completed: false,
    active: true,
  },
  {
    title: "Delivered",
    date: "-",
    completed: false,
    active: false,
  },
];

export default function DrawerTimeline() {
  return (
    <section>
      <div className="flex items-center gap-3 mb-6">
        <Clock3 className="text-primary" size={22} />

        <div>
          <h2 className="text-2xl font-semibold text-foreground">
            Order Timeline
          </h2>

          <p className="text-sm text-secondary">Track your order progress</p>
        </div>
      </div>

      <div className="rounded-2xl border border-default bg-card p-8">
        <div className="relative">
          {timeline.map((step, index) => (
            <div
              key={step.title}
              className="relative flex gap-5 pb-8 last:pb-0"
            >
              

              {index !== timeline.length - 1 && (
                <div
                  className="
                    absolute
                    left-[11px]
                    top-6
                    w-[2px]
                    h-full
                    bg-border
                  "
                />
              )}

              

              <div className="relative z-10">
                {step.completed ? (
                  <CheckCircle2
                    size={24}
                    className="text-success fill-success/10"
                  />
                ) : step.active ? (
                  <Clock3 size={24} className="text-primary" />
                ) : (
                  <Circle size={22} className="text-secondary" />
                )}
              </div>

              

              <div className="flex-1">
                <h3
                  className={`
                    font-semibold
                    ${
                      step.completed || step.active
                        ? "text-foreground"
                        : "text-secondary"
                    }
                  `}
                >
                  {step.title}
                </h3>

                <p className="text-sm text-secondary mt-1">{step.date}</p>
              </div>
            </div>
          ))}
        </div>
      </div>
    </section>
  );
}
*/

"use client";

import { CheckCircle2, Circle, Clock3 } from "lucide-react";

import { OrderSearchResponse } from "@/src/common/types/search";

interface Props {
  order: OrderSearchResponse;
}

const formatDate = (value?: string) => {
  if (!value) return "-";

  return new Date(value).toLocaleString("en-IN", {
    dateStyle: "medium",
    timeStyle: "short",
  });
};

export default function DrawerTimeline({ order }: Props) {
  const timeline = [...(order.orderStatuses ?? [])].sort(
    (a, b) => new Date(a.createdOn).getTime() - new Date(b.createdOn).getTime(),
  );

  if (timeline.length === 0) {
    return (
      <section>
        <div className="flex items-center gap-3 mb-6">
          <Clock3 className="text-primary" size={22} />

          <div>
            <h2 className="text-2xl font-semibold text-foreground">
              Order Timeline
            </h2>

            <p className="text-sm text-secondary">
              No status history available
            </p>
          </div>
        </div>

        <div className="rounded-2xl border border-default bg-card p-8 text-secondary">
          No timeline information available for this order.
        </div>
      </section>
    );
  }

  const latestIndex = timeline.length - 1;

  return (
    <section>
      <div className="flex items-center gap-3 mb-6">
        <Clock3 className="text-primary" size={22} />

        <div>
          <h2 className="text-2xl font-semibold text-foreground">
            Order Timeline
          </h2>

          <p className="text-sm text-secondary">Status history of the order</p>
        </div>
      </div>

      <div className="rounded-2xl border border-default bg-card p-8">
        <div className="relative">
          {timeline.map((status, index) => {
            const completed = index < latestIndex;
            const active = index === latestIndex;

            return (
              <div
                key={`${status.status}-${status.createdOn}`}
                className="relative flex gap-5 pb-8 last:pb-0"
              >
                {index !== latestIndex && (
                  <div
                    className="
                      absolute
                      left-[11px]
                      top-6
                      w-[2px]
                      h-full
                      bg-border
                    "
                  />
                )}

                <div className="relative z-10">
                  {completed ? (
                    <CheckCircle2
                      size={24}
                      className="text-success fill-success/10"
                    />
                  ) : active ? (
                    <Clock3 size={24} className="text-primary" />
                  ) : (
                    <Circle size={22} className="text-secondary" />
                  )}
                </div>

                <div className="flex-1">
                  <h3
                    className={`font-semibold ${
                      completed || active ? "text-foreground" : "text-secondary"
                    }`}
                  >
                    {status.status}
                  </h3>

                  <p className="mt-1 text-sm text-secondary">
                    {formatDate(status.createdOn)}
                  </p>

                  {status.remarks && (
                    <p className="mt-2 text-sm text-secondary">
                      {status.remarks}
                    </p>
                  )}

                  <p className="mt-2 text-xs text-secondary">
                    Updated by {status.createdBy}
                  </p>
                </div>
              </div>
            );
          })}
        </div>
      </div>
    </section>
  );
}