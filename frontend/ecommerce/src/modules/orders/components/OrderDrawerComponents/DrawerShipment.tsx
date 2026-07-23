/*"use client";

import { Truck, MapPin, PackageCheck, Hash } from "lucide-react";

export default function DrawerShipment() {
  return (
    <section>
      <div className="flex items-center gap-3 mb-6">
        <Truck size={22} className="text-primary" />

        <div>
          <h2 className="text-2xl font-semibold text-foreground">
            Shipment Details
          </h2>

          <p className="text-secondary text-sm">Current shipping information</p>
        </div>
      </div>

      <div className="bg-card border border-default rounded-2xl p-8">
        <div className="grid grid-cols-2 gap-8">
          <div>
            <p className="text-secondary text-sm">Courier Partner</p>

            <div className="mt-2 flex items-center gap-2">
              <Truck size={18} />
              <span className="font-semibold text-foreground">Blue Dart</span>
            </div>
          </div>

          <div>
            <p className="text-secondary text-sm">Tracking ID</p>

            <div className="mt-2 flex items-center gap-2">
              <Hash size={18} />
              <span className="font-semibold">BD2349182391</span>
            </div>
          </div>

          <div>
            <p className="text-secondary text-sm">Current Location</p>

            <div className="mt-2 flex items-center gap-2">
              <MapPin size={18} />
              <span>Mumbai Distribution Center</span>
            </div>
          </div>

          <div>
            <p className="text-secondary text-sm">Expected Delivery</p>

            <div className="mt-2 flex items-center gap-2">
              <PackageCheck size={18} />

              <span className="font-semibold text-success">Tomorrow</span>
            </div>
          </div>
        </div>
      </div>
    </section>
  );
}
*/

"use client";

import { AlertCircle, PackageSearch } from "lucide-react";

export default function DrawerShipment() {
  return (
    <section>
      <div className="flex items-center gap-3 mb-6">
        <PackageSearch size={22} className="text-primary" />

        <div>
          <h2 className="text-2xl font-semibold text-foreground">
            Shipment Details
          </h2>

          <p className="text-secondary text-sm">Shipping information</p>
        </div>
      </div>

      <div className="rounded-2xl border border-default bg-card p-8">
        <div className="flex items-start gap-4">
          <AlertCircle className="text-yellow-500 mt-1" size={22} />

          <div>
            <h3 className="font-semibold text-foreground">
              Shipment information unavailable
            </h3>

            <p className="mt-2 text-secondary leading-7">
              This order response does not include shipment details such as
              courier partner, tracking ID, current location, or expected
              delivery date.
            </p>

            <p className="mt-4 text-secondary leading-7">
              Shipment details will appear here once they are provided by the
              backend.
            </p>
          </div>
        </div>
      </div>
    </section>
  );
}