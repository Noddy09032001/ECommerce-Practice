import ProductCard from "@/src/common/components/ProductCard";

const products = [
  {
    itemId: "ITM-1",
    itemName: "iPhone 16",
    sku: "IPH001",
    itemDescription: "Apple iPhone 16 Pro Max",
    sellers: [
      {
        seller: {
          sellerId: "S1",
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
          sellerId: "S2",
          sellerName: "iVenus",
        },
        amount: 99000,
        transportationCharges: 500,
        otherCharges: 100,
        totalCost: 116820,
        availableQuantity: 5,
      },
    ],
  },
];

export default function Products() {
  return (
    <main className="min-h-screen bg-white dark:bg-black text-black dark:text-white p-8 transition-colors">
      <h1 className="text-4xl font-bold mb-8">
        Products
      </h1>

      <div className="grid md:grid-cols-3 gap-6">
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