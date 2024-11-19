package com.yuvraj.user.client.remote;

import com.yuvraj.restclient.BodyRequest;
import com.yuvraj.restclient.OkHttpRestClientAsync;
import com.yuvraj.restclient.RestClientAsync;
import com.yuvraj.restclient.Url;
import com.yuvraj.user.common.*;
import com.yuvraj.user.common.signup.AuthenticatedUser;
import com.yuvraj.user.common.signup.OTPException;
import com.yuvraj.user.common.signup.OTPStatus;
import com.yuvraj.util.json.Json;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class SignInClient {
    private final String baseUrl;
    private final Json json = Json.create();
    private final RestClientAsync restClient = new OkHttpRestClientAsync();

    public CompletableFuture<SessionResponse> sendOTP(Contact contact) {
        Url url = new Url(baseUrl, "auth", "send", "otp");
        BodyRequest bodyRequest = new BodyRequest(url, contact);
        return restClient.post(bodyRequest)
                .thenApply(response -> {
                    var statusCode = response.getStatusCode();
                    var payload = response.getPayload();
                    String exceptionMessage = exceptionMessage(payload);
                    if (statusCode == 200) {
                        return json.decode(payload,
                                SessionResponse.class);
                    }
                    if (statusCode == 400) {
                        throw new BadRequestException(exceptionMessage);
                    } else if (statusCode == 404) {
                        throw new NotFoundException(exceptionMessage);
                    } else {
                        throw new InternalException(exceptionMessage);
                    }
                });
    }

    public CompletableFuture<VerifyResponse> verifyOTP(VerifyRequest request) {
        Url url = new Url(baseUrl, "auth", "verify", "otp");
        BodyRequest bodyRequest = new BodyRequest(url, request);
        return restClient.post(bodyRequest)
                .thenApply(response -> {
                    String exceptionMessage = exceptionMessage(response.getPayload());
                    if (response.getStatusCode() == 200) {
                        return json.decode(response.getPayload(), VerifyResponse.class);
                    }
                    else if (response.getStatusCode() == 400) {
                        throw new BadRequestException(exceptionMessage);
                    }
                    else if(response.getStatusCode() == 429) {
                        throw new OTPException(OTPStatus.MAX_ATTEMPTS_REACHED);
                    }else if(response.getStatusCode() == 404) {
                        throw new OTPException(OTPStatus.NOT_FOUND);
                    }else if (response.getStatusCode() == 401) {
                        var errorPayload = json.decode(response.getPayload(), Result.class);
                        if (errorPayload.getCode().equals("otp-expired")){
                            throw new OTPException(OTPStatus.EXPIRED);
                        }
                        throw new OTPException(OTPStatus.INCORRECT);
                    } else {
                        throw new InternalException(exceptionMessage);
                    }
                });
    }

    public CompletableFuture<AuthenticatedUser> signIn(SignInRequest request) {
        Url url = new Url(baseUrl, "signIn");
        BodyRequest bodyRequest = new BodyRequest(url, request);
        return restClient.post(bodyRequest)
                .thenApply(httpResponse -> {
                    var exceptionMessage = exceptionMessage(httpResponse.getPayload());
                   if (httpResponse.getStatusCode() == 200) {
                       return json.decode(httpResponse.getPayload(), AuthenticatedUser.class);
                   } else if (httpResponse.getStatusCode() == 404) {
                       throw new NotFoundException("user does not exist");
                   }else {
                       throw new InternalException(exceptionMessage);
                   }
                });
    }

    private String exceptionMessage(String payload) {
        return "Failure, reason: ".concat(payload);
    }
}
