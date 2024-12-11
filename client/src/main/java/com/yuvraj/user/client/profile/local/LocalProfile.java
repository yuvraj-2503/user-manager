package com.yuvraj.user.client.profile.local;

import com.yuvraj.filestore.file.File;
import com.yuvraj.user.client.profile.Profile;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.time.Instant;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class LocalProfile implements Profile {
    private final String firstName;
    private final String lastName;
    private final File profilePicture;

    @Getter
    private final Instant lastUpdated;

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public InputStream newPictureStream() {
        return profilePicture == null ? null : profilePicture.read();
    }
}
