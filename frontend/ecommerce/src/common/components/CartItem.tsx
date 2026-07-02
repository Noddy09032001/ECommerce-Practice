interface Props {
  item: any;
}

export default function CartItem({ item }: Props) {
  return (
    <div className="bg-white dark:bg-zinc-900 rounded-xl p-5 border border-zinc-200 dark:border-zinc-800 transition-colors">
      <div className="flex justify-between">
        <div>
          <h2 className="text-lg font-semibold text-black dark:text-white">
            {item.name}
          </h2>

          <p className="text-zinc-600 dark:text-zinc-400">
            Seller: {item.seller}
          </p>

          <p className="text-zinc-600 dark:text-zinc-400">
            Qty: {item.quantity}
          </p>
        </div>

        <div className="text-right">
          <p className="text-zinc-700 dark:text-zinc-300">
            Base: ₹{item.amount}
          </p>

          <p className="text-zinc-700 dark:text-zinc-300">
            Tax: ₹{item.tax}
          </p>

          <p className="font-bold text-xl text-black dark:text-white">
            ₹{item.total}
          </p>
        </div>
      </div>
    </div>
  );
}