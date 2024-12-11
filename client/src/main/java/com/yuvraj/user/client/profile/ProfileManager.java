package com.yuvraj.user.client.profile;

import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj Singh
 */
public interface ProfileManager {
    Profile getProfile();
    CompletableFuture<Void> updateProfile(Profile profile);
    CompletableFuture<Void> syncOnline();
}
