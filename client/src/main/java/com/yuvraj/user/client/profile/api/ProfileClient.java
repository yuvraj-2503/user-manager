package com.yuvraj.user.client.profile.api;

import com.yuvraj.restclient.*;
import com.yuvraj.user.common.InternalException;
import com.yuvraj.user.common.NotFoundException;
import com.yuvraj.user.common.profile.ProfileDTO;
import com.yuvraj.util.json.Json;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj Singh
 */
public class ProfileClient {
    private final String baseURL;
    private final String token;
    private final Json json = Json.create();
    private final RestClientAsync restClient = new OkHttpRestClientAsync();

    public ProfileClient(String baseURL, String token) {
        this.baseURL = baseURL;
        this.token = token;
    }

    public CompletableFuture<Void> updateProfile(ProfileDTO profileDTO) {
        var url = new Url(baseURL, "profile");
        var headers = new Headers();
        headers.add("Authorization", "Bearer " + token);
        BodyRequest request = new BodyRequest(url, headers, profileDTO);
        var response = restClient.patch(request);
        return response.thenAccept((httpResponse -> {
            if (httpResponse.getStatusCode() == 200) {
            } else {
                throw new InternalException(httpResponse.getPayload());
            }
        }));
    }

    public CompletableFuture<ProfileDTO> getProfile(Instant instant) {
        var url = new Url(baseURL, "profile", String.valueOf(instant.toEpochMilli()));
        var headers = new Headers();
        headers.add("Authorization", "Bearer " + token);
        Request request = new Request(url, headers);
        var response = restClient.get(request);
        return response.thenApply((httpResponse -> {
            if (httpResponse.getStatusCode() == 200) {
                return json.decode(httpResponse.getPayload(), ProfileDTO.class);
            } else if (httpResponse.getStatusCode() == 204) {
                throw new NotFoundException("No Content");
            } else if (httpResponse.getStatusCode() == 404) {
                throw new NotFoundException("User Profile not found");
            } else {
                throw new InternalException(httpResponse.getPayload());
            }
        }));
    }
}
