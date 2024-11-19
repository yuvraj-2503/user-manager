package com.yuvraj.user.client.auth;

import com.yuvraj.user.client.remote.SignInClient;
import com.yuvraj.user.common.AuthConfig;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.SignInRequest;
import com.yuvraj.user.common.user.UserData;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class ServerAuthenticator {

    private final AuthConfig authConfig;
    private final SignInClient signInClient;

    public ServerAuthenticator(AuthConfig authConfig, AuthContext authContext) {
        this.authConfig = authConfig;
        this.signInClient = new SignInClient(authContext.userUrl());
    }

    public CompletableFuture<UserData> authenticate(String userId) {
        SignInRequest request = new SignInRequest(userId,
                authConfig.getApp().name(),
                authConfig.getDevice());

        return signInClient.signIn(request)
                .thenApply(user -> new UserData(user.getUserId(), user.getEmailId(),
                                    user.getPhoneNumber(), user.getApiKey()));

    }

}
