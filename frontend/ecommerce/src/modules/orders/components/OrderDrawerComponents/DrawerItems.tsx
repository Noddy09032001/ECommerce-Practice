"use client";

import { Package } from "lucide-react";

interface Props {
  order: any;
}

export default function DrawerItems({ order }: Props) {
  return (
    <section>
      <div className="flex items-center gap-3 mb-6">
        <Package className="text-primary" size={22} />

        <div>
          <h2 className="text-2xl font-semibold text-foreground">
            Order Items
          </h2>

          <p className="text-sm text-secondary">
            {order.items?.length ?? 0} item(s) purchased
          </p>
        </div>
      </div>

      <div className="space-y-5">
        {order.items?.map((item: any) => (
          <div
            key={item.id}
            className="
              border
              border-default
              rounded-2xl
              p-6
              bg-card
              hover:shadow-md
              transition-all
            "
          >
            <div className="flex justify-between items-start">
              {/* Left */}

              <div className="flex gap-5">
                <div className="h-20 w-20 rounded-xl bg-secondary-bg overflow-hidden flex items-center justify-center">
                  {item.image ? (
                    <img
                      src={item.image}
                      alt={item.name}
                      className="h-full w-full object-cover"
                    />
                  ) : (
                    <Package className="text-secondary" size={30} />
                  )}
                </div>

                <div>
                  <h3 className="text-lg font-semibold text-foreground">
                    {item.name}
                  </h3>

                  <p className="mt-1 text-secondary">Seller</p>

                  <p className="font-medium text-foreground">{item.seller}</p>

                  <div className="mt-3 flex gap-6 text-sm text-secondary">
                    <span>
                      Qty : <strong>{item.quantity}</strong>
                    </span>

                    <span>SKU : {item.sku}</span>
                  </div>
                </div>
              </div>

              <div className="text-right">
                <p className="text-secondary text-sm">Unit Price</p>

                <h3 className="text-xl font-bold text-foreground">
                  ₹{item.price.toLocaleString()}
                </h3>

                <p className="mt-3 text-secondary text-sm">Total</p>

                <h4 className="font-semibold text-foreground">
                  ₹{(item.price * item.quantity).toLocaleString()}
                </h4>
              </div>
            </div>
          </div>
        ))}
      </div>
    </section>
  );
}
