"use client";

import OrderStatusBadge from "@/src/common/components/orders/OrderStatusBadge";
import PaymentBadge from "@/src/common/components/orders/PaymentBadge";

interface Props {
  order: any;
  onClick: () => void;
}

export default function OrdersTableRow({ order, onClick }: Props) {
  return (
    <tr
      onClick={onClick}
      className="
        border-b
        border-default
        cursor-pointer
        hover:bg-secondary-bg/50
        transition-colors
      "
    >
      <td className="px-6 py-5">
        <div className="font-semibold text-primary">{order.orderId}</div>
      </td>

      <td className="px-6 py-5 font-semibold">
        ₹{order.amount.toLocaleString()}
      </td>

      <td className="px-6 py-5">
        <PaymentBadge payment={order.payment} />
      </td>

      <td className="px-6 py-5 text-secondary">{order.date}</td>

      <td className="px-6 py-5">
        <OrderStatusBadge status={order.status} />
      </td>

      <td className="px-6 py-5 text-secondary">{order.address}</td>
    </tr>
  );
}
