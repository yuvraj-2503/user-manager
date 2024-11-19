package com.yuvraj.user.client.auth;

import com.yuvraj.user.client.localuser.LocalUserManager;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.user.User;
import com.yuvraj.user.common.user.UserData;
import com.yuvraj.user.common.user.UserImpl;
import lombok.RequiredArgsConstructor;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class ExistingUserAuthenticator {
    private final AuthContext context;

    public User authenticate(String userId) {
        UserData userData = getUser(userId);
        return new UserImpl(userData);
    }

    private UserData getUser(String userId) {
        var localUserManager = new LocalUserManager(context.getBlockPath());
        return localUserManager.get(userId);
    }
}
