package com.yuvraj.user.client.signup;

import com.yuvraj.user.client.localuser.LocalUserManager;
import com.yuvraj.user.client.remote.SignUpClient;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.Contact;
import com.yuvraj.user.common.signup.AuthenticatedUser;
import com.yuvraj.user.common.signup.SignUpRequest;
import com.yuvraj.user.common.user.User;
import com.yuvraj.user.common.user.UserData;
import com.yuvraj.user.common.user.UserImpl;
import lombok.RequiredArgsConstructor;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class UserCreator {
    private final SignUpClient client;
    private final AuthContext context;

    public CompletableFuture<User> create(Contact contact, String sessionId, int otp) {
        return signUp(contact, sessionId, otp).thenApply(authenticatedUser -> {
           UserData userData = getUserData(authenticatedUser);
           new LocalUserManager(context.getBlockPath()).create(userData);
           return new UserImpl(userData);
        });
    }

    private UserData getUserData(AuthenticatedUser authenticatedUser) {
        return new UserData(
                authenticatedUser.getUserId(),
                authenticatedUser.getEmailId(),
                authenticatedUser.getPhoneNumber(),
                authenticatedUser.getApiKey()
        );
    }

    private CompletableFuture<AuthenticatedUser> signUp(Contact contact, String sessionId, int otp) {
        var config = context.getAuthConfig();
        var signUpRequest = new SignUpRequest();
        signUpRequest.setEmailId(contact.getEmailId());
        signUpRequest.setPhoneNumber(contact.getPhoneNumber());
        signUpRequest.setApp(context.getApp().name());
        signUpRequest.setSessionId(sessionId);
        signUpRequest.setOtp(otp);
        signUpRequest.setDevice(config.getDevice());
        return client.signUp(signUpRequest);
    }
}
