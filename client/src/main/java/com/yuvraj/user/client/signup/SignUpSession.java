package com.yuvraj.user.client.signup;

import com.yuvraj.user.common.user.User;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public interface SignUpSession {
    CompletableFuture<Void> sendOTP();

    CompletableFuture<User> signUp(int otp);
}
