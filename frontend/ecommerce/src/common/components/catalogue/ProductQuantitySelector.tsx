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
        className="h-10 w-10 rounded-lg border border-default bg-card text-foreground hover:bg-secondary-bg transition-colors"
      >
        -
      </button>

      <div className="w-12 text-center font-semibold text-foreground">
        {quantity}
      </div>

      <button
        onClick={() => quantity < max && setQuantity(quantity + 1)}
        className="h-10 w-10 rounded-lg border border-default bg-card text-foreground hover:bg-secondary-bg transition-colors"
      >
        +
      </button>
    </div>
  );
}
