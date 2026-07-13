// generating the common configurations for api's

import axios from "axios";

const api = axios.create({
    baseURL: "http://localhost:8080/api/v1",   // setting the common base url
    timeout: 10000,  // setting the timeout between api calls
    // setting the headers descriptions
    headers: {
        "Content-Type": "application/json",
    }
})

export default api;  // exporting the default api structure 