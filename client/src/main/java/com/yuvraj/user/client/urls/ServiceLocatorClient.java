package com.yuvraj.user.client.urls;

import com.yuvraj.user.common.ServerKeys;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public class ServiceLocatorClient {
    public ServiceLocatorClient(String locatorUrl) {

    }

    public CompletableFuture<Map<String, String>> get(){
        Map<String, String> urls = new HashMap<>();
        urls.put(ServerKeys.USER_SERVER, "http://10.0.2.2:8080/api/v1");
        return CompletableFuture.completedFuture(urls);
    }
}
