package com.yuvraj.user.client.remote;

import com.yuvraj.restclient.BodyRequest;
import com.yuvraj.restclient.OkHttpRestClientAsync;
import com.yuvraj.restclient.RestClientAsync;
import com.yuvraj.restclient.Url;
import com.yuvraj.user.common.*;
import com.yuvraj.user.common.signup.AuthenticatedUser;
import com.yuvraj.user.common.signup.OTPException;
import com.yuvraj.user.common.signup.OTPStatus;
import com.yuvraj.user.common.signup.SignUpRequest;
import com.yuvraj.user.common.user.PhoneNumber;
import com.yuvraj.util.json.Json;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public class SignUpClient {
    private final String baseUrl;
    private final Json json = Json.create();
    private final RestClientAsync restClientAsync = new OkHttpRestClientAsync();

    public SignUpClient(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public CompletableFuture<String> sendOTP(String emailId) {
        var url = new Url(baseUrl, "otp", "email");
        var body = json.encode(new EmailRequest(emailId));
        var request = new BodyRequest(url, body);
        var response = restClientAsync.post(request);
        return response.thenApply((res) -> {
            if (res.getStatusCode() == 200){
                SessionResponse sessionResponse = json.decode(res.getPayload(), SessionResponse.class);
                return sessionResponse.getSessionId();
            }else if (res.getStatusCode() == 409){
                throw new ExistException("User already exists by email: " + emailId);
            } else {
                throw new InternalException("Failed to send otp, reason: " + res.getPayload());
            }
        });
    }

    public CompletableFuture<String> sendOTP(PhoneNumber phoneNumber) {
        var url = new Url(baseUrl, "otp", "sms");
        var body = json.encode(phoneNumber);
        var request = new BodyRequest(url, body);
        var response = restClientAsync.post(request);
        return response.thenApply((res) -> {
            if (res.getStatusCode() == 200){
                SessionResponse sessionResponse = json.decode(res.getPayload(), SessionResponse.class);
                return sessionResponse.getSessionId();
            }else if (res.getStatusCode() == 409){
                throw new ExistException("User already exists by phone number: " + phoneNumber);
            } else {
                throw new InternalException("Failed to send otp to phone, reason: " + res.getPayload());
            }
        });
    }

    public CompletableFuture<AuthenticatedUser> signUp(SignUpRequest signUpRequest) {
        var url = new Url(baseUrl, "signup");
        var body = json.encode(signUpRequest);
        var request = new BodyRequest(url, body);
        var response = restClientAsync.post(request);
        return response.thenApply((res) -> {
           if (res.getStatusCode() == 200){
               return json.decode(res.getPayload(), AuthenticatedUser.class);
           }else if (res.getStatusCode() == 409){
               throw new ExistException("User already exists");
           }else if(res.getStatusCode() == 429) {
               throw new OTPException(OTPStatus.MAX_ATTEMPTS_REACHED);
           }else if(res.getStatusCode() == 404) {
               throw new OTPException(OTPStatus.NOT_FOUND);
           }else if (res.getStatusCode() == 401) {
               var errorPayload = json.decode(res.getPayload(), Result.class);
               if (errorPayload.getCode().equals("otp-expired")){
                   throw new OTPException(OTPStatus.EXPIRED);
               }
               throw new OTPException(OTPStatus.INCORRECT);
           } else {
               throw new InternalException("Failed to signup, reason: " + res.getPayload());
           }
        });
    }
}
