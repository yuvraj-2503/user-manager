package com.yuvraj.user.client.profile.local;

import com.yuvraj.user.client.profile.Profile;
import com.yuvraj.user.common.profile.ProfileMetadata;
import com.yuvraj.util.streams.Streams;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.nio.file.Path;
import java.time.Instant;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class LocalProfileUpdater {
    private final Path blockPath;

    public void upsertProfile(String userId, Profile profile, Instant instant) {
        var profileFileManager = new ProfileFileManager(blockPath, userId);
        InputStream inputStream = profile.newPictureStream();
        if (inputStream != null) {
            upsertProfilePic(profileFileManager, inputStream);
        }
        upsertProfileMetadata(profileFileManager, profile, instant);
    }

    private void upsertProfileMetadata(ProfileFileManager profileFileManager,
                                       Profile profile, Instant instant) {
        var file = profileFileManager.get();
        if (file == null) {
            file = profileFileManager.create();
        }
        file.write(getProfileMetadata(profile, instant));
    }

    private void upsertProfilePic(ProfileFileManager profileFileManager, InputStream inputStream) {
        var file = profileFileManager.getPicture();
        if (file == null) {
            file = profileFileManager.createPicture();
        }
        try(var writer = file.write()) {
            Streams.transfer(inputStream, writer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ProfileMetadata getProfileMetadata(Profile profile, Instant updatedOn) {
        ProfileMetadata metadata = new ProfileMetadata();
        metadata.setFirstName(profile.firstName());
        metadata.setLastName(profile.lastName());
        metadata.setUpdatedOn(updatedOn.toEpochMilli());
        return metadata;
    }

}
