"use client";

interface Props {
  order: any;
}

export default function DrawerSummary({ order }: Props) {
  return (
    <div className="space-y-8">
      <div>
        <h2 className="text-2xl font-bold text-foreground">Order Summary</h2>

        <p className="text-secondary mt-1">Complete order information</p>
      </div>

      {/* Information */}

      <div className="bg-card rounded-2xl border border-default p-6">
        <h3 className="font-semibold text-lg mb-6">Information</h3>

        <div className="space-y-5">
          <div>
            <p className="text-secondary text-sm">Order ID</p>

            <p className="font-semibold">{order.orderId}</p>
          </div>

          <div>
            <p className="text-secondary text-sm">Payment Method</p>

            <p>{order.payment}</p>
          </div>

          <div>
            <p className="text-secondary text-sm">Order Date</p>

            <p>{order.date}</p>
          </div>

          <div>
            <p className="text-secondary text-sm">Current Status</p>

            <p className="font-semibold text-primary">{order.status}</p>
          </div>
        </div>
      </div>

      {/* Delivery */}

      <div className="bg-card rounded-2xl border border-default p-6">
        <h3 className="font-semibold text-lg mb-6">Delivery Address</h3>

        <p className="leading-7">{order.address}</p>
      </div>

      {/* Payment */}

      <div className="bg-card rounded-2xl border border-default p-6">
        <h3 className="font-semibold text-lg mb-6">Payment Summary</h3>

        <div className="space-y-4">
          <div className="flex justify-between">
            <span className="text-secondary">Subtotal</span>

            <span>₹100,000</span>
          </div>

          <div className="flex justify-between">
            <span className="text-secondary">Tax</span>

            <span>₹18,000</span>
          </div>

          <div className="flex justify-between">
            <span className="text-secondary">Shipping</span>

            <span>₹600</span>
          </div>

          <hr className="border-default" />

          <div className="flex justify-between text-xl font-bold">
            <span>Grand Total</span>

            <span>₹{order.amount.toLocaleString()}</span>
          </div>
        </div>
      </div>
    </div>
  );
}
