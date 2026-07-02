"use client";

import { ThemeProvider } from "next-themes";
import { CartProvider } from "@/src/common/context/CartContext";

export default function Providers({
    children,
}: {
    children: React.ReactNode;
}) {
    return (
        <ThemeProvider
            attribute="class"
            defaultTheme="dark"
            enableSystem={true}
        >
            <CartProvider>
                {children}
            </CartProvider>
        </ThemeProvider>
    );
}