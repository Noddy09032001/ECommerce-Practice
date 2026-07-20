/*"use client";

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
*/

"use client";

import OrderStatusBadge from "@/src/common/components/orders/OrderStatusBadge";
import PaymentBadge from "@/src/common/components/orders/PaymentBadge";

import { OrderSearchResponse } from "@/src/common/types/search"; 

interface Props {
  order: OrderSearchResponse;
  onClick: () => void;
}

export default function OrdersTableRow({ order, onClick }: Props) {
  const deliveryAddress = [
    order.orderDetails?.address,
    order.orderDetails?.city,
    order.orderDetails?.state,
  ]
    .filter(Boolean)
    .join(", ");

  return (
    <tr
      onClick={onClick}
      className="
        border-b
        border-default
        cursor-pointer
        hover:bg-secondary-bg/60
        transition-colors
      "
    >
      {/* Order ID */}

      <td className="px-6 py-5">
        <div className="font-semibold text-primary">{order.orderId}</div>
      </td>

      {/* Grand Total */}

      <td className="px-6 py-5 font-semibold text-foreground">
        ₹{order.grandTotal.toLocaleString()}
      </td>

      {/* Payment */}

      <td className="px-6 py-5">
        <PaymentBadge payment={order.orderDetails.paymentMode} />
      </td>

      {/* Date */}

      <td className="px-6 py-5 text-secondary">
        {new Date(order.createdOn).toLocaleDateString("en-IN", {
          day: "2-digit",
          month: "short",
          year: "numeric",
        })}
      </td>

      {/* Status */}

      <td className="px-6 py-5">
        <OrderStatusBadge status={order.currentStatus} />
      </td>

      {/* Address */}

      <td className="px-6 py-5 text-secondary max-w-sm truncate">
        {deliveryAddress}
      </td>
    </tr>
  );
}