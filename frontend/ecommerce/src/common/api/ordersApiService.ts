import { API_ROUTES } from "./apiRoutes"
import api from "./axiosconfig"

export const createOrder = async(request: any) => {
    const orderKey = crypto.randomUUID();  // generating a random key for checking idempotency during order creation

    const response = await api.post(API_ROUTES.ORDERS.CREATE_ORDER, request,
        {
            headers: {
                "idempotency-key": orderKey,  // adding the key in the headers for the order request
            }
        }
    );  // calling the create order api for creating a new order 
    if(response)
        return response.data.data;  // returning the data from the response
}