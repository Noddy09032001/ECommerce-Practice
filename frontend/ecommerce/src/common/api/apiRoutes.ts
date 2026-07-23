export const API_ROUTES = {
    ITEMS: {
        "ALL_ITEMS": "/items/allItems",  // fetching all the items
    },
    SEARCH:{
        "ITEM_SEARCH": "search/items-search",  // searching for the items
        "ORDER_SEARCH": "search/orders-search",  // searching for the orders
    },
    ORDERS: {
        "CREATE_ORDER": "orders/create-order",  // creating a new order
        "ALL_ORDERS": "",
    },
    PAYMENTS: {
        "CREATE_PAYMENT": "payments/checkout",  // creating and generating a new stripe payment checkout link
    },
    USERS: "/users",
    SELLERS: "/sellers",

} as const;