package com.yuvraj.user.client.profile;

import com.yuvraj.user.client.profile.api.ServerProfileRetriever;
import com.yuvraj.user.client.profile.api.ServerProfileUpdater;
import com.yuvraj.user.client.profile.local.LocalProfile;
import com.yuvraj.user.client.profile.local.LocalProfileIO;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj Singh
 */
public class ProfileManagerImpl implements ProfileManager {

    private final String baseURL;
    private final String token;
    private final LocalProfileIO localProfileIO;

    private Instant lastUpdated;
    private Profile profile;

    public ProfileManagerImpl(String baseURL, String token, LocalProfileIO localProfileIO) {
        this.baseURL = baseURL;
        this.token = token;
        this.localProfileIO = localProfileIO;
        LocalProfile localProfile = localProfileIO.getProfile();
        this.profile = localProfile;
        this.lastUpdated = localProfile == null ? Instant.EPOCH : localProfile.getLastUpdated();
    }

    @Override
    public Profile getProfile() {
        return profile;
    }

    @Override
    public CompletableFuture<Void> updateProfile(Profile profile) {
        return new ServerProfileUpdater(baseURL, token).update(profile)
                .thenAccept((v) -> {
                    updateLocally(profile);
                });
    }

    private void updateLocally(Profile profile) {
        this.lastUpdated = localProfileIO.updateProfile(profile);
        this.profile = profile;
    }

    @Override
    public CompletableFuture<Void> syncOnline() {
        return new ServerProfileRetriever(baseURL, token).get(lastUpdated)
                .thenAccept((profile) -> {
                    if (profile == null) {
                        return;
                    }
                    updateLocally(profile);
                });
    }
}
