import Link from "next/link";

const Confirm = () => {
  const orderId = "ORD-8F3A91B";

  return (
    <main className="max-w-5xl mx-auto px-8 py-20">
      <div className="bg-white dark:bg-zinc-900 border border-zinc-200 dark:border-zinc-800 rounded-2xl p-16 text-center transition-colors">
        <div className="text-7xl">✅</div>

        <h1 className="mt-6 text-4xl font-bold text-black dark:text-white">
          Order Confirmed
        </h1>

        <p className="mt-4 text-zinc-600 dark:text-zinc-400">
          Thank you for shopping with us.
        </p>

        <div className="mt-10 p-8 bg-zinc-100 dark:bg-black border border-zinc-200 dark:border-zinc-800 rounded-xl">
          <p className="text-zinc-600 dark:text-zinc-400">
            Order ID
          </p>

          <h2 className="mt-3 text-3xl font-bold text-black dark:text-white">
            {orderId}
          </h2>
        </div>

        <div className="mt-10 flex justify-center gap-4">
          <Link href="/orders">
            <button className="px-6 py-3 bg-black dark:bg-white text-white dark:text-black rounded-xl transition">
              Track Order
            </button>
          </Link>

          <Link href="/products">
            <button className="px-6 py-3 bg-zinc-200 dark:bg-zinc-800 text-black dark:text-white rounded-xl transition">
              Continue Shopping
            </button>
          </Link>
        </div>
      </div>
    </main>
  );
};

export default Confirm;