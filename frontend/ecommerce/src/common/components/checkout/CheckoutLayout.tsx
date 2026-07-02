"use client";

import {ReactNode} from "react";

import OrderSummary from "./OrderSummary";
import CheckoutStepper, {CheckoutStep} from "./CheckoutStepper";

interface Props{
    children:ReactNode;
    currentStep:CheckoutStep;
}

export default function CheckoutLayout({children, currentStep}:Props){
    return(
        <div className="min-h-screen bg-white dark:bg-black text-black dark:text-white transition-colors">
            <CheckoutStepper currentStep={currentStep}/>
            <div className="max-w-7xl mx-auto px-6 py-10">
                <div className="grid grid-cols-12 gap-8">
                    <div className="col-span-12 lg:col-span-8">
                        <div className="bg-white dark:bg-zinc-900 border border-zinc-200 dark:border-zinc-800 rounded-xl p-8 min-h-[650px] transition-colors">
                            {children}
                        </div>
                    </div>
                    <div className="col-span-12 lg:col-span-4">
                        <OrderSummary/>
                    </div>
                </div>
            </div>
        </div>
    );
}