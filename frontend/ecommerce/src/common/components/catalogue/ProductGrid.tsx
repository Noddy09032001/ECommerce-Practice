"use client";

import ProductCard from "./ProductCard";
import { Product } from "@/src/common/types/products";

interface Props {
  products: Product[];
  onSelect: (product: Product) => void;
  previewOpen?: boolean;
}

export default function ProductGrid({
  products,
  onSelect,
  previewOpen = false,
}: Props) {
  return (
    <div>
      {/* header */}

      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-3xl font-bold">Product Catalogue</h1>

          <p className="mt-2 text-zinc-500 dark:text-zinc-400">
            Showing {products.length} products
          </p>
        </div>

        <select className="px-4 py-2 rounded-xl border border-zinc-200 dark:border-zinc-700 bg-white dark:bg-zinc-900">
          <option>Price: Low To High</option>

          <option>Price: High To Low</option>

          <option>Latest</option>

          <option>Popularity</option>
        </select>
      </div>

      {/* products */}

      <div
        className={
          previewOpen
            ? "grid grid-cols-1 md:grid-cols-2 gap-6"
            : "grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-6"
        }
      >
        {products.map((product) => (
          <ProductCard
            key={product.itemId}
            product={product}
            onSelect={onSelect}
          />
        ))}
      </div>

      {/* pagination */}

      <div className="mt-16 flex justify-center">
        <div className="flex items-center gap-3">
          <button className="h-10 w-10 rounded-xl border border-zinc-200 dark:border-zinc-700">
            {"<"}
          </button>

          <button className="h-10 w-10 rounded-xl bg-black dark:bg-white text-white dark:text-black">
            1
          </button>

          <button className="h-10 w-10 rounded-xl border border-zinc-200 dark:border-zinc-700">
            2
          </button>

          <button className="h-10 w-10 rounded-xl border border-zinc-200 dark:border-zinc-700">
            3
          </button>

          <button className="h-10 w-10 rounded-xl border border-zinc-200 dark:border-zinc-700">
            {">"}
          </button>
        </div>
      </div>
    </div>
  );
}
