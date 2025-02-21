package com.yuvraj.user.client.manager;

import com.yuvraj.user.client.auth.UserAuthenticator;
import com.yuvraj.user.client.profile.ProfileManager;
import com.yuvraj.user.client.profile.ProfileManagerImpl;
import com.yuvraj.user.client.profile.local.LocalProfileIOImpl;
import com.yuvraj.user.client.signin.SignInSession;
import com.yuvraj.user.client.signin.SignInSessionImpl;
import com.yuvraj.user.client.signup.SignUpSession;
import com.yuvraj.user.client.signup.SignUpSessionImpl;
import com.yuvraj.user.client.urls.URLManager;
import com.yuvraj.user.common.AuthConfig;
import com.yuvraj.user.common.AuthContext;
import com.yuvraj.user.common.Contact;
import com.yuvraj.user.common.user.User;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public class UserManagerImpl implements UserManager {

    private final AuthContext authContext;
    public UserManagerImpl(Path path, AuthConfig authConfig) {
        var urls = new URLManager(authConfig.getLocatorUrl(), path, authConfig.getEnv()).getUrl();
        this.authContext = new AuthContext(path, authConfig, urls, authConfig.getApp());
    }

    @Override
    public SignUpSession createSignUpSession(Contact contact) {
        return new SignUpSessionImpl(authContext, contact);
    }

    @Override
    public SignInSession createSignInSession(Contact contact) {
        return new SignInSessionImpl(authContext, contact);
    }

    @Override
    public CompletableFuture<User> signIn(String userId) {
        var authenticator = new UserAuthenticator(authContext);
        return authenticator.authenticate(userId);
    }

    @Override
    public ProfileManager getProfileManager(User user) {
        var localProfileIO = new LocalProfileIOImpl(user.userId(), authContext.getBlockPath());
        return new ProfileManagerImpl(
                authContext.userUrl(),
                user.apiKey(),
                localProfileIO
        );
    }

    @Override
    public Map<String, String> getUrls() {
        return authContext.getUrls();
    }
}
