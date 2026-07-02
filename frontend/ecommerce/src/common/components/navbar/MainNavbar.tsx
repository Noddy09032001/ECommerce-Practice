"use client";

import Link from "next/link";
import { ShoppingCart, Package, User, Search } from "lucide-react";
import ThemeToggle from "../elements/ThemeToggleButton";
import { useCart } from "@/src/common/context/CartContext";

export default function MainNavbar() {

  const { items } = useCart();

  return (
    <nav className="sticky top-0 z-50 backdrop-blur-xl border-b border-zinc-200 dark:border-zinc-800 bg-white/80 dark:bg-black/80 transition-colors">
      <div className="max-w-7xl mx-auto px-8 h-16 flex items-center justify-between">

        {/* logo */}

        <Link
          href="/products"
          className="text-2xl font-bold text-black dark:text-white"
        >
          ShopEase
        </Link>

        {/* search */}

        <div className="hidden md:flex items-center bg-zinc-100 dark:bg-zinc-900 border border-zinc-200 dark:border-zinc-800 rounded-xl px-4 py-2 w-96 transition-colors">
          <Search
            size={18}
            className="mr-2 text-zinc-500 dark:text-zinc-400"
          />

          <input
            placeholder="Search products..."
            className="bg-transparent outline-none flex-1 text-black dark:text-white placeholder:text-zinc-500"
          />
        </div>

        {/* actions */}

        <div className="flex items-center gap-6">

          <Link
            href="/products"
            className="text-black dark:text-white"
          >
            <Package />
          </Link>

          <Link
            href="/orders"
            className="text-black dark:text-white"
          >
            <User />
          </Link>

          <Link
            href="/cart"
            className="relative text-black dark:text-white"
          >
            <ShoppingCart />

            {items.length > 0 && (
              <span className="absolute -top-2 -right-2 bg-red-500 text-white text-xs h-5 w-5 rounded-full flex items-center justify-center">
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