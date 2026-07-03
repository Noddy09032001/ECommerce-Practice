import type { Metadata } from "next";

import "./globals.css";
import Providers from "./providers";
import MainNavbar from "@/src/common/components/navbar/MainNavbar";

export const metadata: Metadata = {
  title: "Shopify",
  description: "Marketplace Application",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en" suppressHydrationWarning>
      <body className="min-h-screen bg-background text-foreground antialiased transition-colors duration-200">
        <Providers>
          <MainNavbar />
          <main className="min-h-[calc(100vh-4rem)]">{children}</main>
        </Providers>
      </body>
    </html>
  );
}
