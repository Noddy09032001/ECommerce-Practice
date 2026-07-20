import { ItemSearchRequest, OrderSearchRequest } from "../types/search";
import api from "./axiosconfig";
import { API_ROUTES } from "./apiRoutes";

export const searchItems = async(request: ItemSearchRequest) => {
    const response = await api.post(API_ROUTES.SEARCH.ITEM_SEARCH, request);  // calling the api for item search 
    if(response)
        return response.data.data;  // returning the data of the item search filter
}

export const searchOrders = async(request: OrderSearchRequest) => {
    const response = await api.post(API_ROUTES.SEARCH.ORDER_SEARCH, request);  // calling the api for order search
    if(response)
        return response.data.data;  // returning the data for the order search filter
}