"use client";
import CheckoutLayout from "@/src/common/components/checkout/CheckoutLayout";
import { CartItem } from "@/src/common/types/cart";
import { useRouter } from "next/navigation";
import { useEffect, useState } from "react";
import { createOrder } from "@/src/common/api/ordersApiService";


// defining the interface for the items request body
interface ItemRequest{
  id: number,   // id of the current item
  quantity: number,  // the quantity of the item ordered
  sellerId: number,  // the id of the merchant 
}

const Address = () => {
  const router = useRouter();

  const [address, setAddress] = useState({
    street: "",
    city: "",
    state: "",
    country: "",
    pinCode: "",
  });

  // validation for the errors in the fields of the address form
  const [errors, setErrors] = useState({
    street: "",
    city: "",
    state: "",
    country: "",
    pinCode: "",
  });

  const [items, setItems] = useState<CartItem[]>([]);
  useEffect(() => {
    const cart = localStorage.getItem("cart");  // getting the items from the local storage
    if (cart) {
      setItems(JSON.parse(cart));  // parsing into json object
    }
  }, []);


  // getting the details of the current user (using static data for now)
  const user = {
    "email": "rahul.sharma@example.com",
    "phoneNumber": 9876543210
  }

  const validateForm = () => {
    const newErrors = {
      street: "",
      city: "",
      state: "",
      country: "",
      pinCode: "",
    };

    let isValid = true;

    if (!address.street.trim()) {
      newErrors.street = "Street address is required";
      isValid = false;
    }

    if (!address.city.trim()) {
      newErrors.city = "City is required";
      isValid = false;
    }

    if (!address.state.trim()) {
      newErrors.state = "State is required";
      isValid = false;
    }

    if (!address.country.trim()) {
      newErrors.country = "Country is required";
      isValid = false;
    }

    if (!address.pinCode.trim()) {
      newErrors.pinCode = "Pin code is required";
      isValid = false;
    }

    setErrors(newErrors);   // setting the error values 

    return isValid;
  };

  // function for creating the order
  const generateOrder = async () => {
  
    if(!validateForm())  // validations fields
      return;

    // creating a request array of items for creating the order object
    const itemRequest: ItemRequest[] = items.map((item: CartItem) => ({
      id: item.itemId, // setting the item id
      quantity: item.quantity, // setting the quantity
      sellerId: item.sellerId, // setting the id of the merchant associated with the item
    }));

    const reqBody = {
      orderDate: new Date().toISOString(), // getting the current date
      items: itemRequest, // getting the request items
      paymentMode: "CARD", // default payment method
      address: address, // setting the address details
      user: user, // setting the user details
    };

    console.log("The request body: ", reqBody);

    try {
      const response = await createOrder(reqBody) // calling the orders api
      console.log(response)

      const paymentItems = {
        items: response.items,
        orderId: response.orderId,
      }

      // setting the response in the session storage 
      sessionStorage.setItem(
        "currentOrder",
        JSON.stringify(paymentItems)  // setting the items in the session storage 
      );

      router.push("/checkout/payment");  // routing to the payment page after the response 

    } catch (error) {
      console.log(error)
    }
  };

  return (
    <CheckoutLayout currentStep="address">
      <div>
        <h1 className="text-3xl font-bold text-foreground">Delivery Address</h1>

        <p className="mt-2 text-muted">
          Enter the delivery address for your order
        </p>

        <div className="mt-8 space-y-5">
          <input
            value={address.street}
            onChange={(e) => {
              setAddress({
                ...address,
                street: e.target.value,
              });

              setErrors({
                ...errors,
                street: "",
              });
            }}
            placeholder="Street Address"
            className={`w-full p-4 rounded-xl bg-card border outline-none transition-colors ${
              errors.street ? "border-red-500" : "border-default"
            }`}
          />
          {errors.street && (
            <p className="mt-1 text-sm text-red-500">{errors.street}</p>
          )}

          <div className="grid grid-cols-2 gap-4">
            <input
              value={address.city}
              onChange={(e) => {
                setAddress({
                  ...address,
                  city: e.target.value,
                });

                setErrors({
                  ...errors,
                  city: "",
                });
              }}
              placeholder="City"
              className={`p-4 rounded-xl bg-card outline-none transition-colors ${
                errors.city ? "border border-red-500" : "border border-default"
              }`}
            />

            {errors.city && (
              <p className="mt-1 text-sm text-red-500">{errors.city}</p>
            )}

            <input
              value={address.state}
              onChange={(e) => {
                setAddress({
                  ...address,
                  state: e.target.value,
                });

                setErrors({
                  ...errors,
                  state: "",
                });
              }}
              placeholder="State"
              className={`p-4 rounded-xl bg-card outline-none transition-colors ${
                errors.state ? "border border-red-500" : "border border-default"
              }`}
            />

            {errors.state && (
              <p className="mt-1 text-sm text-red-500">{errors.state}</p>
            )}
          </div>

          <div className="grid grid-cols-2 gap-4">
            <input
              value={address.country}
              onChange={(e) => {
                setAddress({
                  ...address,
                  country: e.target.value,
                });

                setErrors({
                  ...errors,
                  country: "",
                });
              }}
              placeholder="Country"
              className={`p-4 rounded-xl bg-card outline-none transition-colors ${
                errors.country
                  ? "border border-red-500"
                  : "border border-default"
              }`}
            />

            {errors.country && (
              <p className="mt-1 text-sm text-red-500">{errors.country}</p>
            )}

            <input
              value={address.pinCode}
              onChange={(e) => {
                setAddress({
                  ...address,
                  pinCode: e.target.value,
                });

                setErrors({
                  ...errors,
                  pinCode: "",
                });
              }}
              placeholder="Pin Code"
              className={`p-4 rounded-xl bg-card outline-none transition-colors ${
                errors.pinCode
                  ? "border border-red-500"
                  : "border border-default"
              }`}
            />

            {errors.pinCode && (
              <p className="mt-1 text-sm text-red-500">{errors.pinCode}</p>
            )}
          </div>
        </div>

        <div className="mt-10 flex justify-end">
          <button
            onClick={generateOrder}
            className="px-8 py-3 bg-primary text-primary-foreground rounded-xl font-semibold transition cursor-pointer hover:opacity-90"
          >
            Place Order
          </button>
        </div>
      </div>
    </CheckoutLayout>
  );
};

export default Address;
