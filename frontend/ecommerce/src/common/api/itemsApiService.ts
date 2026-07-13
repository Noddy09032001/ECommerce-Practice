import api from "./axiosconfig";
import { API_ROUTES } from "./apiRoutes";

// function to fetch all the items 
export const fetchAllItems = async() => {
    const response = await api.get(API_ROUTES.ITEMS.ALL_ITEMS);
    if(response){
        return response.data;  // returning the data from the response
    }
}