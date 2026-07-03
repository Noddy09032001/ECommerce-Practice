"use client";

interface Props {
  quantity: number;
  setQuantity: (quantity: number) => void;
  max: number;
}

export default function ProductQuantitySelector({
  quantity,
  setQuantity,
  max,
}: Props) {
  return (
    <div className="flex items-center gap-4">
      <button
        onClick={() => quantity > 1 && setQuantity(quantity - 1)}
        className="h-10 w-10 rounded-lg border border-zinc-200 dark:border-zinc-700"
      >
        -
      </button>

      <div className="w-12 text-center font-semibold">{quantity}</div>

      <button
        onClick={() => quantity < max && setQuantity(quantity + 1)}
        className="h-10 w-10 rounded-lg border border-zinc-200 dark:border-zinc-700"
      >
        +
      </button>
    </div>
  );
}
