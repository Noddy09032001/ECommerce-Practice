interface Props {
  item: any;
}

export default function CartItem({ item }: Props) {
  return (
    <div className="bg-card rounded-xl p-5 border border-default transition-colors">
      <div className="flex justify-between">
        <div>
          <h2 className="text-lg font-semibold text-foreground">{item.name}</h2>

          <p className="text-secondary">Seller: {item.seller}</p>

          <p className="text-secondary">Qty: {item.quantity}</p>
        </div>

        <div className="text-right">
          <p className="text-secondary">Base: ₹{item.amount}</p>

          <p className="text-secondary">Tax: ₹{item.tax}</p>

          <p className="font-bold text-xl text-foreground">₹{item.total}</p>
        </div>
      </div>
    </div>
  );
}
