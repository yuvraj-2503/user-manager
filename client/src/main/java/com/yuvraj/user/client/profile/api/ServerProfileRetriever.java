package com.yuvraj.user.client.profile.api;

import com.yuvraj.user.client.profile.Profile;
import com.yuvraj.user.common.NotFoundException;
import com.yuvraj.user.common.profile.ProfileDTO;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class ServerProfileRetriever {
    private final String baseURL;
    private final String token;

    public CompletableFuture<Profile> get(Instant lastSynced) {
        var profileFuture = new CompletableFuture<Profile>();
        var profileClient = new ProfileClient(baseURL, token);
        profileClient.getProfile(lastSynced).whenComplete((profile, throwable) -> {
            if (throwable != null) {
                handleError(throwable, profileFuture);
            } else {
                handleSuccess(profile, profileFuture);
            }
        });
        return profileFuture;
    }

    private void handleSuccess(ProfileDTO profile, CompletableFuture<Profile> profileFuture) {
        if (profile != null) {
            profileFuture.complete(new ServerProfile(profile));
        } else {
            profileFuture.complete(null);
        }
    }

    private void handleError(Throwable throwable, CompletableFuture<Profile> profileFuture) {
        if (throwable instanceof NotFoundException) {
            profileFuture.complete(null);
        } else {
            profileFuture.completeExceptionally(throwable.getCause());
        }
    }
}
