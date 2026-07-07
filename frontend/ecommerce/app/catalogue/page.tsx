"use client";

import { useState } from "react";

import FilterSidebar from "@/src/common/components/catalogue/FilterSidebar";
import ProductGrid from "@/src/common/components/catalogue/ProductGrid";
import ProductPreview from "@/src/common/components/catalogue/ProductPreview";

import { Product } from "@/src/common/types/products";

const products: Product[] = [
  {
    itemId: "ITM-1",
    itemName: "iPhone 16 Pro Max",
    sku: "IPH001",
    itemDescription: "Apple iPhone 16 Pro Max 256GB Titanium Edition",
    images: [
      {
        id: "1",
        url: "https://images.unsplash.com/photo-1592750475338-74b7b21085ab",
      },
      {
        id: "2",
        url: "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9",
      },
      {
        id: "3",
        url: "https://images.unsplash.com/photo-1580910051074-3eb694886505",
      },
    ],
    sellers: [
      {
        seller: {
          sellerId: "SELLER-1",
          sellerName: "Vijay Sales",
        },
        amount: 100000,
        transportationCharges: 500,
        otherCharges: 100,
        totalCost: 118600,
        availableQuantity: 10,
      },
      {
        seller: {
          sellerId: "SELLER-2",
          sellerName: "iVenus",
        },
        amount: 99000,
        transportationCharges: 200,
        otherCharges: 50,
        totalCost: 116820,
        availableQuantity: 5,
      },
    ],
  },

  {
    itemId: "ITM-2",
    itemName: "AirPods Pro",
    sku: "AIR001",
    itemDescription: "Apple AirPods Pro 2nd Generation",
    images: [
      {
        id: "1",
        url: "https://images.unsplash.com/photo-1606220945770-b5b6c2c55bf1",
      },
      {
        id: "2",
        url: "https://images.unsplash.com/photo-1572569511254-d8f925fe2cbb",
      },
      {
        id: "3",
        url: "https://images.unsplash.com/photo-1545127398-14699f92334b",
      },
    ],
    sellers: [
      {
        seller: {
          sellerId: "SELLER-1",
          sellerName: "Vijay Sales",
        },
        amount: 22000,
        transportationCharges: 100,
        otherCharges: 50,
        totalCost: 26000,
        availableQuantity: 25,
      },
    ],
  },
];

export default function CataloguePage() {
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null);

  return (
    <main className="min-h-screen bg-background text-foreground transition-colors duration-200">
      <div className="max-w-[1900px] mx-auto p-6">
        <div
          className={
            selectedProduct
              ? "grid grid-cols-[280px_minmax(0,1fr)_500px] gap-6"
              : "grid grid-cols-[280px_minmax(0,1fr)] gap-6"
          }
        >
          {/* filters */}

          <FilterSidebar />

          {/* catalogue */}

          <ProductGrid
            products={products}
            onSelect={setSelectedProduct}
            previewOpen={selectedProduct !== null}
          />

          {/* preview */}

          {selectedProduct && <ProductPreview product={selectedProduct} onClose={function (): void {
            throw new Error("Function not implemented.");
          } } />}
        </div>
      </div>
    </main>
  );
}
