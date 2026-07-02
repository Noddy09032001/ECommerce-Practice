import type { Metadata} from "next";

import "./globals.css";
import Providers from "./providers";
import MainNavbar from "@/src/common/components/navbar/MainNavbar";

export const metadata: Metadata = {
    title: "ShopEase",
    description: "Marketplace Application",
};

export default function RootLayout({children,}: Readonly<{children: React.ReactNode;}>) {
    return (
        <html
            lang="en"
            suppressHydrationWarning>
            <body>
                <Providers>
                    <div
                        className = "min-h-screen bg-background text-foreground">
                        <MainNavbar/>
                        <main>
                            {children}
                        </main>
                    </div>
                </Providers>
            </body>
        </html>
    );
}