package com.yuvraj.user.client.profile.local;

import com.yuvraj.user.client.profile.Profile;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.time.Instant;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class LocalProfileIOImpl implements LocalProfileIO {
    private final String userId;
    private final Path blockPath;

    @Override
    public LocalProfile getProfile() {
        var retriever = new LocalProfileRetriever(blockPath);
        return retriever.getProfile(userId);
    }

    @Override
    public Instant updateProfile(Profile profile) {
        Instant now = Instant.now();
        var profileUpdater = new LocalProfileUpdater(blockPath);
        profileUpdater.upsertProfile(userId, profile, now);
        return now;
    }
}
