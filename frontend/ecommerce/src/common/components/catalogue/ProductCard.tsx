"use client";

import { Product } from "@/src/common/types/products";

interface Props {
  product: Product;
  onSelect: (product: Product) => void;
}

export default function ProductCard({ product, onSelect }: Props) {
  const cheapestSeller = [...product.sellers].sort(
    (a, b) => a.totalCost - b.totalCost,
  )[0];

  return (
    <div
      onClick={() => onSelect(product)}
      className="bg-card border border-default rounded-2xl overflow-hidden cursor-pointer hover:shadow-2xl hover:-translate-y-1 transition-all duration-300"
    >
      {/* main image */}

      <div className="relative h-72 bg-secondary-bg flex items-center justify-center transition-colors">
        <img
          src={product.images[0]?.url}
          alt={product.itemName}
          className="h-56 object-contain"
        />

        {/* side thumbnails */}

        <div className="absolute bottom-4 right-4 flex gap-2">
          {product.images.slice(1, 3).map((image) => (
            <div
              key={image.id}
              className="h-14 w-14 rounded-lg border border-default bg-card overflow-hidden transition-colors"
            >
              <img src={image.url} className="h-full w-full object-cover" />
            </div>
          ))}
        </div>
      </div>

      {/* details */}

      <div className="p-5">
        <h2 className="text-lg font-bold text-foreground truncate">
          {product.itemName}
        </h2>

        <p className="mt-2 text-sm text-secondary line-clamp-2">
          {product.itemDescription}
        </p>

        <div className="mt-5 flex justify-between items-center">
          <div>
            <p className="text-xs text-secondary">Starting From</p>

            <h3 className="text-2xl font-bold text-foreground">
              ₹{cheapestSeller.totalCost.toLocaleString()}
            </h3>
          </div>

          <div className="text-right">
            <p className="text-xs text-secondary">Seller</p>

            <p className="font-medium text-foreground">
              {cheapestSeller.seller.sellerName}
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
