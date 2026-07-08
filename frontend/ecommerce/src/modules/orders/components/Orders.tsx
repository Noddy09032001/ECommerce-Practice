"use client";

import { useState } from "react";

import OrdersToolbar from "./OrdersToolbar";
import OrdersTable from "./OrdersTable";
import OrdersPagination from "./OrdersPagination";
import OrderDrawer from "./OrderDrawer";

const orders = [
  {
    orderId: "ORD-123456",
    amount: 125000,
    payment: "CARD",
    date: "12 Jan 2025",
    status: "Pending",
    address: "Mumbai, Maharashtra",
  },

  {
    orderId: "ORD-123457",
    amount: 89000,
    payment: "UPI",
    date: "10 Jan 2025",
    status: "Delivered",
    address: "Pune, Maharashtra",
  },

  {
    orderId: "ORD-123458",
    amount: 32000,
    payment: "COD",
    date: "08 Jan 2025",
    status: "Cancelled",
    address: "Delhi",
  },
];

export default function Orders() {
  const [activeTab, setActiveTab] = useState("All Orders");
  const [currentPage, setCurrentPage] = useState(1);
  const [selectedOrder, setSelectedOrder] = useState<any>(null);

  return (
    <main className="min-h-screen bg-background p-8">
      <h1 className="text-4xl font-bold text-foreground">My Orders</h1>
      <p className="mt-2 text-secondary">View and manage all your purchases</p>
      <OrdersToolbar activeTab={activeTab} setActiveTab={setActiveTab} />
      <OrdersTable orders={orders} onSelectOrder={setSelectedOrder} />
      <OrdersPagination currentPage={currentPage} totalPages={5} onPageChange={setCurrentPage}/>

      {selectedOrder && (
        <OrderDrawer order={selectedOrder} onClose={() => setSelectedOrder(null)} open={selectedOrder !== null}/>
      )}
    </main>
  );
}
