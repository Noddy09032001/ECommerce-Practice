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
          <h1 className="text-3xl font-bold text-foreground">
            Product Catalogue
          </h1>

          <p className="mt-2 text-secondary">
            Showing {products.length} products
          </p>
        </div>

        <select className="px-4 py-2 rounded-xl border border-default bg-card text-foreground transition-colors">
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
          <button className="h-10 w-10 rounded-xl border border-default bg-card text-foreground transition-colors">
            {"<"}
          </button>

          <button className="h-10 w-10 rounded-xl bg-primary text-primary-foreground transition-colors">
            1
          </button>

          <button className="h-10 w-10 rounded-xl border border-default bg-card text-foreground transition-colors">
            2
          </button>

          <button className="h-10 w-10 rounded-xl border border-default bg-card text-foreground transition-colors">
            3
          </button>

          <button className="h-10 w-10 rounded-xl border border-default bg-card text-foreground transition-colors">
            {">"}
          </button>
        </div>
      </div>
    </div>
  );
}
