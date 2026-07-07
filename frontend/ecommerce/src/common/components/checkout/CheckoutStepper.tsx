/*"use client";

export type CheckoutStep = "cart" | "address" | "payment" | "confirm";

interface Props {
  currentStep: CheckoutStep;
}

const steps: CheckoutStep[] = ["cart", "address", "payment", "confirm"];

export default function CheckoutStepper({ currentStep }: Props) {
  const current = steps.indexOf(currentStep);

  return (
    <div className="sticky top-16 z-40 bg-background border-b border-default transition-colors">
      <div className="max-w-7xl mx-auto py-6 flex justify-center items-center">
        {steps.map((step, index) => (
          <div key={step} className="flex items-center">
            <div className="flex flex-col items-center">
              <div
                className={`h-10 w-10 rounded-full flex items-center justify-center font-bold transition-colors ${
                  index <= current
                    ? "bg-foreground text-background"
                    : "bg-secondary-bg text-secondary"
                }`}
              >
                {index + 1}
              </div>

              <span className="mt-2 capitalize text-sm text-secondary">
                {step}
              </span>
            </div>

            {index < steps.length - 1 && (
              <div
                className={`w-28 h-[2px] mx-4 transition-colors ${
                  index < current ? "bg-foreground" : "bg-secondary-bg"
                }`}
              />
            )}
          </div>
        ))}
      </div>
    </div>
  );
}*/

"use client";

export type CheckoutStep = "cart" | "address" | "payment" | "confirm";

interface Props {
  currentStep: CheckoutStep;
}

const steps: CheckoutStep[] = ["cart", "address", "payment", "confirm"];

export default function CheckoutStepper({ currentStep }: Props) {
  const current = steps.indexOf(currentStep);

  return (
    <div className="sticky top-16 z-40 bg-background border-b border-default transition-colors">
      <div className="max-w-7xl mx-auto py-6 flex justify-center items-center">
        {steps.map((step, index) => (
          <div key={step} className="flex items-center">
            <div className="flex flex-col items-center">
              <div
                className={`h-10 w-10 rounded-full flex items-center justify-center font-bold transition-colors ${
                  index <= current ? "bg-active" : "bg-inactive"
                }`}
              >
                {index + 1}
              </div>

              <span className="mt-2 capitalize text-sm text-secondary">
                {step}
              </span>
            </div>

            {index < steps.length - 1 && (
              <div
                className={`w-28 h-[2px] mx-4 transition-colors ${
                  index < current ? "line-active" : "line-inactive"
                }`}
              />
            )}
          </div>
        ))}
      </div>
    </div>
  );
}