package com.yuvraj.user.client.signin;

import com.yuvraj.user.common.VerifyResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public interface SignInSession {
    CompletableFuture<Void> sendOTP();

    CompletableFuture<Void> verifyOTP(int otp);

    VerifyResponse getVerifyResponse();
}
