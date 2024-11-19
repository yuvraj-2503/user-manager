package com.yuvraj.user.client.auth;

import com.yuvraj.user.client.localuser.LocalUserManager;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.user.User;
import com.yuvraj.user.common.user.UserImpl;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class NewUserAuthenticator {
    private final AuthContext context;

    public CompletableFuture<User> authenticate(String userId) {
        ServerAuthenticator serverAuthenticator = new ServerAuthenticator(
                context.getAuthConfig(),
                context
        );

        return serverAuthenticator.authenticate(userId)
                .thenApply(userData -> {
                    LocalUserManager localUserManager = new LocalUserManager(context.getBlockPath());
                    localUserManager.create(userData);
                    return new UserImpl(userData);
                });
    }
}
