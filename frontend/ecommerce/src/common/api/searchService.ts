import { request } from "http";
import { ItemSearchRequest } from "../types/search";
import api from "./axiosconfig";
import { API_ROUTES } from "./apiRoutes";

export const searchItems = async(request: ItemSearchRequest) => {
    const response = await api.post(API_ROUTES.SEARCH.ITEM_SEARCH, request);  // calling the api for item search 
    if(response)
        return response.data.data;  // returning the data of the search filter
}