package com.yuvraj.user.client.profile.local;

import com.yuvraj.user.client.profile.Profile;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.time.Instant;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class LocalProfileRetriever {
    private final Path blockPath;

    public LocalProfile getProfile(String userId) {
        var fileManager = new ProfileFileManager(blockPath, userId);
        var file = fileManager.get();
        if (file == null) {
            return null;
        }

        var metaData = file.read();
        return new LocalProfile(metaData.getFirstName(),
                                metaData.getLastName(),
                                fileManager.getPicture(),
                                Instant.ofEpochMilli(metaData.getUpdatedOn()));
    }

}
