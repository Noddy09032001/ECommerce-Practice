/*"use client";

import OrdersTableRow from "./OrdersTableRow";

interface Props {
  orders: any[];
  onSelectOrder: (order: any) => void;
}

export default function OrdersTable({ orders, onSelectOrder }: Props) {
  return (
    <div
      className="
        mt-6
        bg-card
        border
        border-default
        rounded-2xl
        overflow-hidden
      "
    >
      <div className="overflow-x-auto">
        <table className="w-full">
          <thead className="bg-secondary-bg">
            <tr>
              <th className="px-6 py-5 text-left">Order ID</th>
              <th className="px-6 py-5 text-left">Total Amount</th>
              <th className="px-6 py-5 text-left">Payment</th>
              <th className="px-6 py-5 text-left">Date</th>
              <th className="px-6 py-5 text-left">Status</th>
              <th className="px-6 py-5 text-left">Delivery Address</th>
            </tr>
          </thead>

          <tbody>
            {orders.map((order) => (
              <OrdersTableRow
                key={order.orderId}
                order={order}
                onClick={() => onSelectOrder(order)}
              />
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}*/

"use client";

import OrdersTableRow from "./OrdersTableRow";
import { OrderSearchResponse } from "@/src/common/types/search"; 

interface Props {
  orders: OrderSearchResponse[];
  onSelectOrder: (order: OrderSearchResponse) => void;
}

export default function OrdersTable({ orders, onSelectOrder }: Props) {
  return (
    <div
      className="
        mt-6
        bg-card
        border
        border-default
        rounded-2xl
        overflow-hidden
      "
    >
      <div className="overflow-x-auto">
        <table className="w-full">
          <thead className="bg-secondary-bg">
            <tr>
              <th className="px-6 py-5 text-left font-semibold">Order ID</th>

              <th className="px-6 py-5 text-left font-semibold">
                Total Amount
              </th>

              <th className="px-6 py-5 text-left font-semibold">Payment</th>
              <th className="px-6 py-5 text-left font-semibold">Date</th>
              <th className="px-6 py-5 text-left font-semibold">Status</th>
              <th className="px-6 py-5 text-left font-semibold">
                Delivery Address
              </th>
            </tr>
          </thead>

          <tbody>
            {orders.length > 0 ? (
              orders.map((order) => (
                <OrdersTableRow
                  key={order.orderId}
                  order={order}
                  onClick={() => onSelectOrder(order)}
                />
              ))
            ) : (
              <tr>
                <td colSpan={6} className="py-16 text-center text-secondary">
                  No orders found.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}