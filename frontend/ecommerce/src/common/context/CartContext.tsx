"use client";

import { createContext, useContext, useEffect, useState } from "react";
import { CartItem } from "../types/cart";

interface CartContextType {
  items: CartItem[];

  addItem: (item: CartItem) => void;
  removeItem: (itemId: number, sellerId: number) => void;
  updateQuantity: (itemId: number, sellerId: number, quantity: number) => void;
  clearCart: () => void;
}

const CartContext = createContext<CartContextType | null>(null);

export function CartProvider({ children }: { children: React.ReactNode }) {
  const [items, setItems] = useState<CartItem[]>([]);

  useEffect(() => {
    const cart = localStorage.getItem("cart");

    if (cart) setItems(JSON.parse(cart));
  }, []);

  useEffect(() => {
    localStorage.setItem("cart", JSON.stringify(items));
  }, [items]);

  const addItem = (newItem: CartItem) => {
    const existing = items.find(
      (item) =>
        item.itemId === newItem.itemId && item.sellerId === newItem.sellerId,
    );

    if (existing) {
      setItems(
        items.map((item) => {
          if (
            item.itemId === newItem.itemId &&
            item.sellerId === newItem.sellerId
          ) {
            return {
              ...item,
              quantity: item.quantity + newItem.quantity,
            };
          }

          return item;
        }),
      );

      return;
    }

    setItems((prev) => [...prev, newItem]);
  };

  const removeItem = (itemId: number, sellerId: number) => {
    setItems(
      items.filter(
        (item) => !(item.itemId === itemId && item.sellerId === sellerId),
      ),
    );
  };

  const updateQuantity = (
    itemId: number,
    sellerId: number,
    quantity: number,
  ) => {
    setItems(
      items.map((item) => {
        if (item.itemId === itemId && item.sellerId === sellerId) {
          return {
            ...item,
            quantity,
          };
        }

        return item;
      }),
    );
  };

  const clearCart = () => setItems([]);

  return (
    <CartContext.Provider
      value={{
        items,
        addItem,
        removeItem,
        updateQuantity,
        clearCart,
      }}
    >
      {children}
    </CartContext.Provider>
  );
}

export function useCart() {
  const context = useContext(CartContext);
  if (!context) 
    throw new Error("useCart must be inside CartProvider");
  return context;
}
