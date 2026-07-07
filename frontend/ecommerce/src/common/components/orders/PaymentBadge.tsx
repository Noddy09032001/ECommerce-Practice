"use client";

interface Props {
  payment: string;
}

export default function PaymentBadge({ payment }: Props) {
  return (
    <span className="px-3 py-1 rounded-lg bg-secondary-bg text-secondary text-xs font-medium">
      {payment}
    </span>
  );
}
