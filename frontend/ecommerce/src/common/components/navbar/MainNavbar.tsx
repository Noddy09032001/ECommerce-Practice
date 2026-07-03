"use client";

import Link from "next/link";
import { ShoppingCart, Package, User, Search, MapPin } from "lucide-react";
import ThemeToggle from "../elements/ThemeToggleButton";
import { useCart } from "@/src/common/context/CartContext";

export default function MainNavbar() {
  const { items } = useCart();

  // temporary address data for the current user
  const currentAddress = {
    city: "Mumbai",
    state: "Maharashtra",
    pinCode: "400001",
  };

  return (
    <nav className="sticky top-0 z-50 backdrop-blur-xl border-b border-default bg-background/80 transition-colors">
      <div className="max-w-7xl mx-auto px-8 h-16 flex items-center justify-between">
        {/* logo + address */}

        <div className="flex items-center gap-8">
          <Link href="/products" className="text-2xl font-bold text-foreground">
            Shopify
          </Link>

          <button className="flex items-center gap-2 px-3 py-2 rounded-xl hover:bg-secondary-bg transition-colors">
            <MapPin size={18} className="text-foreground" />

            <div className="flex flex-col text-left">
              <span className="text-xs text-secondary">Deliver to</span>

              <span className="text-sm font-medium text-foreground">
                {currentAddress.city}, {currentAddress.state}{" "}
                {currentAddress.pinCode}
              </span>
            </div>
          </button>
        </div>

        {/* search */}

        <div className="hidden md:flex items-center bg-secondary-bg border border-default rounded-xl px-4 py-2 w-96 transition-colors">
          <Search size={18} className="mr-2 text-secondary" />

          <input
            placeholder="Search products..."
            className="bg-transparent outline-none flex-1 text-foreground placeholder:text-secondary"
          />
        </div>

        {/* actions */}

        <div className="flex items-center gap-6">
          <Link
            href="/products"
            className="text-foreground hover:text-primary transition-colors"
          >
            <Package />
          </Link>

          <Link
            href="/orders"
            className="text-foreground hover:text-primary transition-colors"
          >
            <User />
          </Link>

          <Link
            href="/cart"
            className="relative text-foreground hover:text-primary transition-colors"
          >
            <ShoppingCart />

            {items.length > 0 && (
              <span className="absolute -top-2 -right-2 bg-danger text-white text-xs h-5 w-5 rounded-full flex items-center justify-center">
                {items.length}
              </span>
            )}
          </Link>

          <ThemeToggle />
        </div>
      </div>
    </nav>
  );
}
