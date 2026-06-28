package com.code.ecommerce.common.constants;

import com.code.ecommerce.exceptions.SameRequestException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RequestTracker {
    private static final Set<String> requestKeysList = ConcurrentHashMap.newKeySet();  // storing the incoming request keys
    private static final Set<String> orderKeysList = ConcurrentHashMap.newKeySet();  // storing the incoming order keys

    public static void addRequestKey(String key){
        if(!requestKeysList.add(key))
            throw new SameRequestException("Request with the same details has already been sent once");
    }

    public static void addOrderKeys(String key){
        if(!orderKeysList.add(key))
            throw new SameRequestException("Request with the same order keys has been sent once");
    }
}
