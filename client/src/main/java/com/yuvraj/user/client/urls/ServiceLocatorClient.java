package com.yuvraj.user.client.urls;

import com.yuvraj.restclient.*;
import com.yuvraj.user.common.Env;
import com.yuvraj.util.json.Json;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class ServiceLocatorClient {
    private final String locatorUrl;
    private final Env env;

    private final RestClientAsync restClientAsync = new OkHttpRestClientAsync();
    private final Json json = Json.create();

    public CompletableFuture<Map<String, String>> get(){
        var url = new Url(locatorUrl, "endpoints");
        var queryParams = new QueryParams();
        queryParams.add("env", env.name());
        url.setQueryParams(queryParams);
        Request request = new Request(url);
        return restClientAsync.get(request)
                .thenApply((response) -> {
                    if (response.getStatusCode() == 200) {
                        Map<String, String> urls = json.decode(response.getPayload(), Map.class);
                        return urls;
                    } else  {
                        return new HashMap<>();
                    }
                });
    }
}
