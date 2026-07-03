"use client";

import { useState } from "react";

export default function FilterSidebar() {
  const [priceRange, setPriceRange] = useState({
    min: 0,
    max: 500000,
  });

  return (
    <aside className="sticky top-20 h-[calc(100vh-6rem)] overflow-y-auto bg-card border border-default rounded-2xl p-6 transition-colors">
      {/* title */}

      <div className="flex justify-between items-center">
        <h2 className="text-2xl font-bold">Filters</h2>
      </div>

      {/* category */}

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

      {/* price */}

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

      {/* sellers */}

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

      {/* availability */}

      <div className="mt-10">
        <h3 className="font-semibold text-lg">Availability</h3>

        <div className="mt-4">
          <label className="flex items-center gap-3 cursor-pointer">
            <input type="checkbox" className="accent-primary" />

            <span>In Stock Only</span>
          </label>
        </div>
      </div>

      {/* sorting */}

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
}
