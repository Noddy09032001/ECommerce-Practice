import Link from "next/link";

const Confirm = () => {
  const orderId = "ORD-8F3A91B";

  return (
    <main className="max-w-5xl mx-auto px-8 py-20">
      <div className="bg-card border border-default rounded-2xl p-16 text-center transition-colors">
        <div className="text-7xl">✅</div>

        <h1 className="mt-6 text-4xl font-bold text-foreground">
          Order Confirmed
        </h1>

        <p className="mt-4 text-secondary">Thank you for shopping with us.</p>

        <div className="mt-10 p-8 bg-secondary-bg border border-default rounded-xl transition-colors">
          <p className="text-secondary">Order ID</p>

          <h2 className="mt-3 text-3xl font-bold text-foreground">{orderId}</h2>
        </div>

        <div className="mt-10 flex justify-center gap-4">
          <Link href="/orders">
            <button className="px-6 py-3 bg-primary text-primary-foreground rounded-xl transition cursor-pointer">
              Track Order
            </button>
          </Link>

          <Link href="/products">
            <button className="px-6 py-3 bg-secondary-bg text-foreground border border-default rounded-xl transition cursor-pointer">
              Continue Shopping
            </button>
          </Link>
        </div>
      </div>
    </main>
  );
};

export default Confirm;
