/*"use client";

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

    

      <div
        className={
          previewOpen
            ? "grid grid-cols-1 md:grid-cols-2 gap-6"
            : "grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-6"
        }
      >
        {products.map((product) => (
          <ProductCard
            key={product.sku}
            product={product}
            onSelect={onSelect}
          />
        ))}
      </div>

      

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
*/

"use client";

import ProductCard from "./ProductCard";

import { Product } from "@/src/common/types/products";

interface Props {
  products: Product[];

  loading: boolean;

  totalElements: number;

  currentPage: number;

  totalPages: number;

  previewOpen?: boolean;

  onSelect: (product: Product) => void;

  onPageChange: (page: number) => void;
}

export default function ProductGrid({
  products,
  loading,
  totalElements,
  currentPage,
  totalPages,
  previewOpen = false,
  onSelect,
  onPageChange,
}: Props) {
  return (
    <div>
      {/* Header */}

      <div className="flex justify-between items-center mb-8">
        <div>
          <h1 className="text-3xl font-bold text-foreground">
            Product Catalogue
          </h1>

          <p className="mt-2 text-secondary">
            Showing {products.length} of {totalElements} products
          </p>
        </div>
      </div>

      {/* Loading */}

      {loading && (
        <div className="flex items-center justify-center h-96">
          <div className="flex flex-col items-center gap-4">
            <div className="h-10 w-10 rounded-full border-4 border-primary border-t-transparent animate-spin" />

            <p className="text-secondary">Loading products...</p>
          </div>
        </div>
      )}

      {/* Empty State */}

      {!loading && products.length === 0 && (
        <div className="h-96 flex flex-col items-center justify-center border border-dashed border-default rounded-2xl bg-card">
          <h2 className="text-2xl font-semibold text-foreground">
            No Products Found
          </h2>

          <p className="mt-2 text-secondary">Try changing the filters.</p>
        </div>
      )}

      {/* Product Cards */}

      {!loading && products.length > 0 && (
        <div
          className={
            previewOpen
              ? "grid grid-cols-1 md:grid-cols-2 gap-6"
              : "grid grid-cols-1 md:grid-cols-2 xl:grid-cols-3 2xl:grid-cols-4 gap-6"
          }
        >
          {products.map((product) => (
            <ProductCard
              key={product.sku}
              product={product}
              onSelect={onSelect}
            />
          ))}
        </div>
      )}

      {/* Pagination */}

      {!loading && totalPages > 1 && (
        <div className="mt-14 flex justify-center items-center gap-3">
          {/* Previous */}

          <button
            disabled={currentPage === 0}
            onClick={() => onPageChange(currentPage - 1)}
            className="h-10 px-4 rounded-xl border border-default bg-card disabled:opacity-40 disabled:cursor-not-allowed hover:bg-secondary-bg transition-colors cursor-pointer"
          >
            Previous
          </button>

          {/* Page Numbers */}

          {Array.from({ length: totalPages }, (_, index) => (
            <button
              key={index}
              onClick={() => onPageChange(index)}
              className={`h-10 w-10 rounded-xl transition-colors cursor-pointer ${
                currentPage === index
                  ? "bg-primary text-white"
                  : "border border-default bg-card hover:bg-secondary-bg"
              }`}
            >
              {index + 1}
            </button>
          ))}

          {/* Next */}

          <button
            disabled={currentPage === totalPages - 1}
            onClick={() => onPageChange(currentPage + 1)}
            className="h-10 px-4 rounded-xl border border-default bg-card disabled:opacity-40 disabled:cursor-not-allowed hover:bg-secondary-bg transition-colors cursor-pointer"
          >
            Next
          </button>
        </div>
      )}
    </div>
  );
}