/*"use client";

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
    <div className="sticky top-20 h-[calc(100vh-6rem)] overflow-y-auto bg-card border border-default rounded-2xl p-6 transition-colors">
      

      <ProductImageGallery images={product.images} />

      
      <div className="mt-8">
        <h1 className="text-3xl font-bold text-foreground">
          {product.itemName}
        </h1>

        <p className="mt-3 text-secondary">{product.itemDescription}</p>

        <p className="mt-3 text-sm text-secondary">SKU : {product.sku}</p>
      </div>

    

      <div className="mt-8">
        <label className="font-semibold text-foreground">Seller</label>

        <select
          className="mt-3 w-full p-4 rounded-xl border border-default bg-card text-foreground outline-none transition-colors"
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

     

      <div className="mt-8 space-y-4">
        <div className="flex justify-between text-secondary">
          <span>Base Price</span>

          <span>₹{selectedSeller.amount}</span>
        </div>

        <div className="flex justify-between text-secondary">
          <span>Transportation</span>

          <span>₹{selectedSeller.transportationCharges}</span>
        </div>

        <div className="flex justify-between text-secondary">
          <span>Other Charges</span>

          <span>₹{selectedSeller.otherCharges}</span>
        </div>

        <hr className="border-default" />

        <div className="flex justify-between text-2xl font-bold text-foreground">
          <span>Total</span>

          <span>₹{selectedSeller.totalCost}</span>
        </div>
      </div>

      

      <div className="mt-8">
        <p className="text-success">
          Available : {selectedSeller.availableQuantity}
        </p>
      </div>

     

      <div className="mt-8">
        <label className="font-semibold text-foreground">Quantity</label>

        <div className="mt-3">
          <ProductQuantitySelector
            quantity={quantity}
            setQuantity={setQuantity}
            max={selectedSeller.availableQuantity}
          />
        </div>
      </div>

      

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
        className="mt-10 w-full py-4 rounded-xl bg-primary text-white font-semibold transition-colors hover:opacity-90"
      >
        Add To Cart
      </button>
    </div>
  );
}*/

"use client";

import { useState } from "react";
import { X } from "lucide-react";

import { Product } from "@/src/common/types/products";
import { useCart } from "@/src/common/context/CartContext";

import ProductImageGallery from "./ProductImageGallery";
import ProductQuantitySelector from "./ProductQuantitySelector";

interface Props {
  product: Product;
  onClose: () => void;
}

export default function ProductPreview({ product, onClose }: Props) {
  const { addItem } = useCart();

  const cheapest = [...product.sellers].sort(
    (a, b) => a.totalCost - b.totalCost,
  )[0];

  const [selectedSeller, setSelectedSeller] = useState(cheapest);

  const [quantity, setQuantity] = useState(1);

  return (
    <div className="sticky top-20 h-[calc(100vh-6rem)] overflow-y-auto bg-card border border-default rounded-2xl p-6 transition-colors">
      {/* header */}

      <div className="flex justify-between items-center mb-6">
        <h2 className="text-lg font-semibold text-foreground">
          Product Details
        </h2>

        <button
          onClick={onClose}
          className="h-10 w-10 rounded-xl border border-default bg-secondary-bg flex items-center justify-center text-secondary hover:bg-card transition-colors"
        >
          <X size={18} />
        </button>
      </div>

      {/* gallery */}

      <ProductImageGallery images={product.images} />

      {/* title */}

      <div className="mt-8">
        <h1 className="text-3xl font-bold text-foreground">
          {product.itemName}
        </h1>

        <p className="mt-3 text-secondary">{product.itemDescription}</p>

        <p className="mt-3 text-sm text-secondary">SKU : {product.sku}</p>
      </div>

      {/* seller */}

      <div className="mt-8">
        <label className="font-semibold text-foreground">Seller</label>

        <select
          className="mt-3 w-full p-4 rounded-xl border border-default bg-card text-foreground outline-none transition-colors"
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
        <div className="flex justify-between text-secondary">
          <span>Base Price</span>
          <span>₹{selectedSeller.amount}</span>
        </div>

        <div className="flex justify-between text-secondary">
          <span>Transportation</span>
          <span>₹{selectedSeller.transportationCharges}</span>
        </div>

        <div className="flex justify-between text-secondary">
          <span>Other Charges</span>
          <span>₹{selectedSeller.otherCharges}</span>
        </div>

        <hr className="border-default" />

        <div className="flex justify-between text-2xl font-bold text-foreground">
          <span>Total</span>
          <span>₹{selectedSeller.totalCost}</span>
        </div>
      </div>

      {/* inventory */}

      <div className="mt-8">
        <p className="text-success">
          Available : {selectedSeller.availableQuantity}
        </p>
      </div>

      {/* quantity */}

      <div className="mt-8">
        <label className="font-semibold text-foreground">Quantity</label>

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
        className="mt-10 w-full py-4 rounded-xl bg-primary text-white font-semibold transition-colors hover:opacity-90 cursor-pointer"
      >
        Add To Cart
      </button>
    </div>
  );
}