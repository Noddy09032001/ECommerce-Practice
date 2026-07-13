export const API_ROUTES = {
    ITEMS: {
        "ALL_ITEMS": "/items/allItems",  // fetching all the items
    },
    SEARCH:{
        "ITEM_SEARCH": "search/items-search",  // searching for the items
        "ORDER_SEARCH": "search/order-search",  // searching for the orders
    },
    ORDERS: "/orders",
    USERS: "/users",
    SELLERS: "/sellers",

} as const;