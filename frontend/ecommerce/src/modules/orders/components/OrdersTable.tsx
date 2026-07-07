/*"use client";

const orders = [
  {
    orderId: "ORD-123456",
    amount: 125000,
    payment: "CARD",
    date: "12 Jan 2025",
    status: "Pending",
    address: "Mumbai, Maharashtra",
  },

  {
    orderId: "ORD-123457",
    amount: 89000,
    payment: "UPI",
    date: "10 Jan 2025",
    status: "Delivered",
    address: "Pune, Maharashtra",
  },

  {
    orderId: "ORD-123458",
    amount: 32000,
    payment: "COD",
    date: "08 Jan 2025",
    status: "Cancelled",
    address: "Delhi",
  },
];

export default function OrdersTable() {
  return (
    <div className="mt-8 bg-card border border-default rounded-2xl overflow-hidden transition-colors">
      <div className="overflow-x-auto">
        <table className="w-full">
        

          <thead className="bg-secondary-bg">
            <tr>
              <th className="px-6 py-5 text-left font-semibold text-foreground">
                Order ID
              </th>

              <th className="px-6 py-5 text-left font-semibold text-foreground">
                Total Amount
              </th>

              <th className="px-6 py-5 text-left font-semibold text-foreground">
                Payment
              </th>

              <th className="px-6 py-5 text-left font-semibold text-foreground">
                Date
              </th>

              <th className="px-6 py-5 text-left font-semibold text-foreground">
                Status
              </th>

              <th className="px-6 py-5 text-left font-semibold text-foreground">
                Delivery Address
              </th>
            </tr>
          </thead>


          <tbody>
            {orders.map((order) => (
              <tr
                key={order.orderId}
                className="
                  border-t
                  border-default
                  cursor-pointer
                  hover:bg-secondary-bg
                  transition-colors
                "
              >
                <td className="px-6 py-5 font-semibold text-primary">
                  {order.orderId}
                </td>

                <td className="px-6 py-5 text-foreground">
                  ₹{order.amount.toLocaleString()}
                </td>

                <td className="px-6 py-5 text-secondary">{order.payment}</td>
                <td className="px-6 py-5 text-secondary">{order.date}</td>
                <td className="px-6 py-5">{order.status}</td>
                <td className="px-6 py-5 text-secondary">{order.address}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}*/

"use client";

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
}