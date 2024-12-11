package com.yuvraj.user.client.manager;

import com.yuvraj.user.client.profile.ProfileManager;
import com.yuvraj.user.client.signin.SignInSession;
import com.yuvraj.user.client.signup.SignUpSession;
import com.yuvraj.user.common.Contact;
import com.yuvraj.user.common.user.User;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj
 */
public interface UserManager {
    SignUpSession createSignUpSession(Contact contact);

    SignInSession createSignInSession(Contact contact);

    CompletableFuture<User> signIn(String userId);

    ProfileManager getProfileManager(User user);
}
