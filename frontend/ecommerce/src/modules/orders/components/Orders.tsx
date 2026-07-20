"use client";

import { useCallback, useEffect, useState } from "react";

import OrdersHeader from "./OrdersHeader";
import OrdersToolbar from "./OrdersToolbar";
import OrdersTable from "./OrdersTable";
import OrdersPagination from "./OrdersPagination";
import OrderDrawer from "./OrderDrawer";

import { searchOrders } from "@/src/common/api/searchService"; 
import { OrderSearchRequest, OrderSearchResponse, } from "@/src/common/types/search";

export default function Orders() {
  const [orders, setOrders] = useState<OrderSearchResponse[]>([]);

  const [activeTab, setActiveTab] = useState("All Orders");

  const [currentPage, setCurrentPage] = useState(1);

  const [totalPages, setTotalPages] = useState(1);

  const [selectedOrder, setSelectedOrder] =
    useState<OrderSearchResponse | null>(null);

  const [loading, setLoading] = useState(false);

  const [filters, setFilters] = useState({
    searchText: "",
    fromDate: "",
    toDate: "",
    minAmount: "",
    maxAmount: "",
  });

  /**
   * Loads the orders from the backend.
   */
  const loadOrders = useCallback(async () => {
    try {
      setLoading(true);

      const request: OrderSearchRequest = {
        userName: "Rahul Sharma",
        email: "rahul.sharma@example.com",
        page: currentPage - 1,
        size: 10,

        searchText:
          filters.searchText.trim() === "" ? undefined : filters.searchText,

        currentStatus:
          activeTab === "All Orders" ? undefined : activeTab.toUpperCase(),

        minOrderAmount:
          filters.minAmount === "" ? undefined : Number(filters.minAmount),

        maxOrderAmount:
          filters.maxAmount === "" ? undefined : Number(filters.maxAmount),

        fromDate:
          filters.fromDate === "" ? undefined : `${filters.fromDate}T00:00:00`,

        toDate:
          filters.toDate === "" ? undefined : `${filters.toDate}T23:59:59`,

        sortBy: "DATE_DESC",
      };

      const response = await searchOrders(request);

      setOrders(response.content);

      setTotalPages(response.totalPages);
    } catch (error) {
      console.error("Failed to fetch orders", error);

      setOrders([]);

      setTotalPages(1);
    } finally {
      setLoading(false);
    }
  }, [activeTab, currentPage, filters]);

  // reload whenever the filter changes
  useEffect(() => {
    loadOrders();
  }, [loadOrders]);

  // resetting the page whenever the filter changes
  useEffect(() => {
    setCurrentPage(1);
  }, [activeTab, filters]);

  return (
    <main className="min-h-screen bg-background p-8">
      <OrdersHeader />

      <OrdersToolbar
        activeTab={activeTab}
        setActiveTab={setActiveTab}
        filters={filters}
        setFilters={setFilters}
      />

      {loading ? (
        <div className="mt-12 text-center text-secondary">
          Loading orders...
        </div>
      ) : (
        <>
          <OrdersTable orders={orders} onSelectOrder={setSelectedOrder} />

          <OrdersPagination
            currentPage={currentPage}
            totalPages={totalPages}
            onPageChange={setCurrentPage}
          />
        </>
      )}

      {selectedOrder && (
        <OrderDrawer
          open={selectedOrder !== null}
          order={selectedOrder}
          onClose={() => setSelectedOrder(null)}
        />
      )}
    </main>
  );
}