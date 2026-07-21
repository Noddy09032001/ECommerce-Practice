package com.code.ecommerce.common.constants;

import com.code.ecommerce.exceptions.SameRequestException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RequestTracker {
    private static final Set<String> requestKeysList = ConcurrentHashMap.newKeySet();  // storing the incoming request keys
    private static final Set<String> orderKeysList = ConcurrentHashMap.newKeySet();  // storing the incoming order keys
    private static final Set<String> paymentKeysList = ConcurrentHashMap.newKeySet();  // storing the incoming payment keys

    public static Boolean addRequestKey(String key){
        if(!requestKeysList.add(key))
            throw new SameRequestException("Request with the same details has already been sent once");
        return true;  // can proceed with the request as no duplicate key found
    }

    public static Boolean addOrderKeys(String key){
        if(!orderKeysList.add(key))
            throw new SameRequestException("Request with the same order keys has been sent once");
        return true;  // can place the order as no duplicate keys found
    }

    public static Boolean addPaymentKeys(String key){
        if(!paymentKeysList.add(key))
            throw new SameRequestException("Request with the same payment keys has been sent once");
        return true;  // can place the payment as no duplicate keys found
    }
}
