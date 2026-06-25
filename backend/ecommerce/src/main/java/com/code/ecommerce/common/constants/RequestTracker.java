package com.code.ecommerce.common.constants;

import com.code.ecommerce.exceptions.SameRequestException;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RequestTracker {
    private static final Set<String> requestKeysList = ConcurrentHashMap.newKeySet();  // storing the incoming request keys
    public static void addRequestKey(String key){
        if(!requestKeysList.add(key))
            throw new SameRequestException("Request with the same details has already been sent once");
    }
}
