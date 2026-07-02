import ProductCard from "@/src/common/components/ProductCard";
import { Product } from "@/src/common/types/products";

const products: Product[] = [
  {
    itemId: "ITM-1",
    itemName: "iPhone 16 Pro Max",
    sku: "IPH001",
    itemDescription: "Apple iPhone 16 Pro Max 256 GB",

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
    itemName: "Airpods Pro",
    sku: "AIR001",
    itemDescription: "Apple Airpods Pro Gen 2",

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
  return (
    <main className="min-h-screen bg-white dark:bg-black text-black dark:text-white p-8 transition-colors">
      <div className="flex justify-between items-center mb-10">
        <h1 className="text-4xl font-bold">
          Product Catalogue
        </h1>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        {products.map((product) => (
          <ProductCard
            key={product.itemId}
            product={product}
          />
        ))}
      </div>
    </main>
  );
}