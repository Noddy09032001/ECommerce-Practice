"use client";

import { useState } from "react";
import { Product } from "../types/products";
import { useCart } from "@/src/common/context/CartContext";

export default function ProductCard({ product }: { product: Product }) {

  const { addItem } = useCart();

  const cheapest = [...product.sellers].sort(
    (a, b) => a.totalCost - b.totalCost,
  )[0];

  const [selectedSeller, setSelectedSeller] = useState(cheapest);

  return (
    <div className="bg-white dark:bg-zinc-900 rounded-xl border border-zinc-200 dark:border-zinc-800 p-5 transition-colors">

      {/* product image */}

      <div className="h-52 bg-zinc-100 dark:bg-zinc-800 rounded-lg mb-5" />

      {/* name */}

      <h2 className="text-xl font-bold text-black dark:text-white">
        {product.itemName}
      </h2>

      {/* description */}

      <p className="text-zinc-600 dark:text-zinc-400 mt-2">
        {product.itemDescription}
      </p>

      {/* sku */}

      <p className="text-zinc-500 text-sm mt-2">
        SKU : {product.sku}
      </p>

      {/* seller */}

      <div className="mt-5">
        <label className="text-sm text-zinc-700 dark:text-zinc-300">
          Seller
        </label>

        <select
          className="w-full mt-2 p-2 bg-zinc-100 dark:bg-zinc-800 border border-zinc-200 dark:border-zinc-700 rounded text-black dark:text-white"
          onChange={(e) =>
            setSelectedSeller(product.sellers[Number(e.target.value)])
          }
        >
          {product.sellers.map((seller, index) => (
            <option key={index} value={index}>
              {seller.seller.sellerName}

              {" - ₹"}

              {seller.totalCost}
            </option>
          ))}
        </select>
      </div>

      {/* pricing */}

      <div className="mt-5 space-y-1">

        <div className="flex justify-between text-zinc-700 dark:text-zinc-300">
          <span>
            Base Price
          </span>

          <span>
            ₹{selectedSeller.amount}
          </span>
        </div>

        <div className="flex justify-between text-zinc-700 dark:text-zinc-300">
          <span>
            Transport
          </span>

          <span>
            ₹{selectedSeller.transportationCharges}
          </span>
        </div>

        <div className="flex justify-between text-zinc-700 dark:text-zinc-300">
          <span>
            Other Charges
          </span>

          <span>
            ₹{selectedSeller.otherCharges}
          </span>
        </div>

        <div className="flex justify-between font-bold text-xl mt-3 text-black dark:text-white">
          <span>
            Total
          </span>

          <span>
            ₹{selectedSeller.totalCost}
          </span>
        </div>

      </div>

      {/* inventory */}

      <div className="mt-3 text-sm text-green-600 dark:text-green-400">
        Available: {selectedSeller.availableQuantity}
      </div>

      {/* add to cart */}

      <button
        onClick={() =>
          addItem({
            itemId: product.itemId,
            itemName: product.itemName,
            sellerId: selectedSeller.seller.sellerId,
            sellerName: selectedSeller.seller.sellerName,
            quantity: 1,
            amount: selectedSeller.amount,
            transportationCharges: selectedSeller.transportationCharges,
            otherCharges: selectedSeller.otherCharges,
            totalCost: selectedSeller.totalCost,
          })
        }
        className="mt-6 w-full bg-black dark:bg-white text-white dark:text-black py-3 rounded-lg font-semibold transition"
      >
        Add To Cart
      </button>

    </div>
  );
}