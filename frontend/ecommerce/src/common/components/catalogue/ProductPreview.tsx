"use client";

import { useState } from "react";

import { Product } from "@/src/common/types/products";
import { useCart } from "@/src/common/context/CartContext";

import ProductImageGallery from "./ProductImageGallery";
import ProductQuantitySelector from "./ProductQuantitySelector";

interface Props {
  product: Product;
}

export default function ProductPreview({ product }: Props) {
  const { addItem } = useCart();

  const cheapest = [...product.sellers].sort(
    (a, b) => a.totalCost - b.totalCost,
  )[0];

  const [selectedSeller, setSelectedSeller] = useState(cheapest);

  const [quantity, setQuantity] = useState(1);

  return (
    <div className="sticky top-20 h-[calc(100vh-6rem)] overflow-y-auto bg-white dark:bg-zinc-950 border border-zinc-200 dark:border-zinc-800 rounded-2xl p-6">
      {/* gallery */}

      <ProductImageGallery images={product.images} />

      {/* title */}

      <div className="mt-8">
        <h1 className="text-3xl font-bold">{product.itemName}</h1>

        <p className="mt-3 text-zinc-500 dark:text-zinc-400">
          {product.itemDescription}
        </p>

        <p className="mt-3 text-sm text-zinc-500">SKU : {product.sku}</p>
      </div>

      {/* seller */}

      <div className="mt-8">
        <label className="font-semibold">Seller</label>

        <select
          className="mt-3 w-full p-4 rounded-xl border border-zinc-200 dark:border-zinc-700 bg-white dark:bg-zinc-900"
          onChange={(e) =>
            setSelectedSeller(product.sellers[Number(e.target.value)])
          }
        >
          {product.sellers.map((seller, index) => (
            <option key={index} value={index}>
              {seller.seller.sellerName}
            </option>
          ))}
        </select>
      </div>

      {/* pricing */}

      <div className="mt-8 space-y-4">
        <div className="flex justify-between">
          <span>Base Price</span>

          <span>₹{selectedSeller.amount}</span>
        </div>

        <div className="flex justify-between">
          <span>Transportation</span>

          <span>₹{selectedSeller.transportationCharges}</span>
        </div>

        <div className="flex justify-between">
          <span>Other Charges</span>

          <span>₹{selectedSeller.otherCharges}</span>
        </div>

        <hr className="border-zinc-200 dark:border-zinc-800" />

        <div className="flex justify-between text-2xl font-bold">
          <span>Total</span>

          <span>₹{selectedSeller.totalCost}</span>
        </div>
      </div>

      {/* inventory */}

      <div className="mt-8">
        <p className="text-green-600 dark:text-green-400">
          Available : {selectedSeller.availableQuantity}
        </p>
      </div>

      {/* quantity */}

      <div className="mt-8">
        <label className="font-semibold">Quantity</label>

        <div className="mt-3">
          <ProductQuantitySelector
            quantity={quantity}
            setQuantity={setQuantity}
            max={selectedSeller.availableQuantity}
          />
        </div>
      </div>

      {/* add to cart */}

      <button
        onClick={() =>
          addItem({
            itemId: product.itemId,
            itemName: product.itemName,
            sellerId: selectedSeller.seller.sellerId,
            sellerName: selectedSeller.seller.sellerName,
            quantity,
            amount: selectedSeller.amount,
            transportationCharges: selectedSeller.transportationCharges,
            otherCharges: selectedSeller.otherCharges,
            totalCost: selectedSeller.totalCost,
          })
        }
        className="mt-10 w-full py-4 rounded-xl bg-black dark:bg-white text-white dark:text-black font-semibold transition"
      >
        Add To Cart
      </button>
    </div>
  );
}
