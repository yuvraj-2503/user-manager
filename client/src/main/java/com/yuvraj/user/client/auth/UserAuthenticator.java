package com.yuvraj.user.client.auth;

import com.yuvraj.user.client.localuser.LocalUserManager;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.user.User;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class UserAuthenticator {
    private final AuthContext context;

    public CompletableFuture<User> authenticate(String userId) {
        var localManager = new LocalUserManager(context.getBlockPath());
        if (localManager.exists(userId)) {
            var existingUserAuthenticator = new ExistingUserAuthenticator(context);
            User user = existingUserAuthenticator.authenticate(userId);
            return CompletableFuture.completedFuture(user);
        }else {
            var newUserAuthenticator = new NewUserAuthenticator(context);
            return newUserAuthenticator.authenticate(userId);
        }
    }
}
