/*"use client";

import { useState } from "react";

export default function FilterSidebar() {
  const [priceRange, setPriceRange] = useState({
    min: 0,
    max: 500000,
  });

  return (
    <aside className="sticky top-20 h-[calc(100vh-6rem)] overflow-y-auto bg-card border border-default rounded-2xl p-6 transition-colors">

      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-bold">Filters</h2>
      </div>

      <div className="mt-8">
        <h3 className="font-semibold text-lg">Category</h3>

        <div className="mt-4 space-y-3">
          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="h-4 w-4 accent-primary" />

            <span>Mobile Phones</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="h-4 w-4 accent-primary" />

            <span>Laptops</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="h-4 w-4 accent-primary" />

            <span>Tablets</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="h-4 w-4 accent-primary" />

            <span>Audio</span>
          </label>
        </div>
      </div>

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Price Range</h3>

        <div className="mt-5">
          <div className="flex gap-3">
            <input
              type="number"
              value={priceRange.min}
              onChange={(e) =>
                setPriceRange({
                  ...priceRange,
                  min: Number(e.target.value),
                })
              }
              className="w-full p-3 rounded-xl border border-default bg-secondary-bg outline-none"
            />

            <input
              type="number"
              value={priceRange.max}
              onChange={(e) =>
                setPriceRange({
                  ...priceRange,
                  max: Number(e.target.value),
                })
              }
              className="w-full p-3 rounded-xl border border-default bg-secondary-bg outline-none"
            />
          </div>

          <input
            type="range"
            min="0"
            max="500000"
            value={priceRange.max}
            onChange={(e) =>
              setPriceRange({
                ...priceRange,
                max: Number(e.target.value),
              })
            }
            className="mt-6 w-full accent-primary"
          />
        </div>
      </div>

      

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Seller</h3>

        <div className="mt-4 space-y-3">
          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="accent-primary" />

            <span>Vijay Sales</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="accent-primary" />

            <span>iVenus</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="accent-primary" />

            <span>Reliance Digital</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="accent-primary" />

            <span>Croma</span>
          </label>
        </div>
      </div>

      

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Availability</h3>

        <div className="mt-4">
          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="accent-primary" />

            <span>In Stock Only</span>
          </label>
        </div>
      </div>

      

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Sort By</h3>

        <div className="mt-4 space-y-3">
          <label className="flex items-center gap-3 cursor-pointer">
            <input
              type="radio"
              name="sort"
              defaultChecked
              className="accent-primary"
            />

            <span>Price Low To High</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="radio" name="sort" className="accent-primary" />

            <span>Price High To Low</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="radio" name="sort" className="accent-primary" />

            <span>Newest First</span>
          </label>

          <label className="flex items-center gap-3 cursor-pointer">
            <input type="radio" name="sort" className="accent-primary" />

            <span>Popularity</span>
          </label>
        </div>
      </div>
    </aside>
  );
}*/

"use client";

import { ItemSearchRequest } from "@/src/common/types/search";

interface Props {
  filters: ItemSearchRequest;
  onChange: (filters: ItemSearchRequest) => void;
}

/**
 * Static values for now.
 * Later these can be fetched from the backend.
 */
const categories = ["Mobile Phones", "Laptops", "Tablets", "Audio"];

const sellers = [
  {
    id: "SELLER-1",
    name: "Vijay Sales",
  },
  {
    id: "SELLER-2",
    name: "iVenus",
  },
  {
    id: "SELLER-3",
    name: "Reliance Digital",
  },
  {
    id: "SELLER-4",
    name: "Croma",
  },
];

export default function FilterSidebar({ filters, onChange }: Props) {
  /**
   * Category selection
   */
  const handleCategory = (category: string, checked: boolean) => {
    if (checked) {
      onChange({
        ...filters,
        categories: [...filters.categories, category],
      });
    } else {
      onChange({
        ...filters,
        categories: filters.categories.filter((c) => c !== category),
      });
    }
  };

  /**
   * Seller selection
   */
  const handleSeller = (sellerId: string, checked: boolean) => {
    if (checked) {
      onChange({
        ...filters,
        sellerIds: [...filters.sellerIds, sellerId],
      });
    } else {
      onChange({
        ...filters,
        sellerIds: filters.sellerIds.filter((id) => id !== sellerId),
      });
    }
  };

  return (
    <aside className="sticky top-20 h-[calc(100vh-6rem)] overflow-y-auto bg-card border border-default rounded-2xl p-6 transition-colors">
      {/* Header */}

      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-bold">Filters</h2>

        <button
          className="text-sm text-primary hover:underline"
          onClick={() =>
            onChange({
              categories: [],
              sellerIds: [],
              minPrice: undefined,
              maxPrice: undefined,
              searchText: "",
              sortBy: "NAME",
              page: 0,
              size: filters.size,
            })
          }
        >
          Clear
        </button>
      </div>

      {/* Search */}

      <div className="mt-8">
        <h3 className="font-semibold text-lg">Search</h3>

        <input
          value={filters.searchText}
          placeholder="Search products..."
          className="mt-4 w-full p-3 rounded-xl border border-default bg-secondary-bg outline-none"
          onChange={(e) =>
            onChange({
              ...filters,
              searchText: e.target.value,
            })
          }
        />
      </div>

      {/* Categories */}

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Category</h3>

        <div className="mt-4 space-y-3">
          {categories.map((category) => (
            <label
              key={category}
              className="flex items-center gap-3 cursor-pointer"
            >
              <input
                type="checkbox"
                checked={filters.categories.includes(category)}
                className="accent-primary"
                onChange={(e) => handleCategory(category, e.target.checked)}
              />

              <span>{category}</span>
            </label>
          ))}
        </div>
      </div>

      {/* Price */}

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Price Range</h3>

        <div className="mt-5 flex gap-3">
          <input
            type="number"
            placeholder="Min"
            value={filters.minPrice ?? ""}
            className="w-full p-3 rounded-xl border border-default bg-secondary-bg outline-none"
            onChange={(e) =>
              onChange({
                ...filters,
                minPrice: e.target.value ? Number(e.target.value) : undefined,
              })
            }
          />

          <input
            type="number"
            placeholder="Max"
            value={filters.maxPrice ?? ""}
            className="w-full p-3 rounded-xl border border-default bg-secondary-bg outline-none"
            onChange={(e) =>
              onChange({
                ...filters,
                maxPrice: e.target.value ? Number(e.target.value) : undefined,
              })
            }
          />
        </div>
      </div>

      {/* Sellers */}

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Sellers</h3>

        <div className="mt-4 space-y-3">
          {sellers.map((seller) => (
            <label
              key={seller.id}
              className="flex items-center gap-3 cursor-pointer"
            >
              <input
                type="checkbox"
                checked={filters.sellerIds.includes(seller.id)}
                className="accent-primary"
                onChange={(e) => handleSeller(seller.id, e.target.checked)}
              />

              <span>{seller.name}</span>
            </label>
          ))}
        </div>
      </div>

      {/* Sorting */}

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Sort By</h3>

        <select
          value={filters.sortBy}
          className="mt-4 w-full rounded-xl border border-default bg-secondary-bg p-3 outline-none"
          onChange={(e) =>
            onChange({
              ...filters,
              sortBy: e.target.value as "NAME" | "PRICE_ASC" | "PRICE_DESC",
            })
          }
        >
          <option value="NAME">Product Name</option>
          <option value="PRICE_ASC">Price : Low → High</option>
          <option value="PRICE_DESC">Price : High → Low</option>
        </select>
      </div>
    </aside>
  );
}