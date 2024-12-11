package com.yuvraj.user.client.profile.api;

import com.yuvraj.user.client.profile.Profile;
import com.yuvraj.user.common.profile.ProfileDTO;
import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Base64;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class ServerProfile implements Profile {
    private final ProfileDTO profileDTO;

    @Override
    public String firstName() {
        return profileDTO.getFirstName();
    }

    @Override
    public String lastName() {
        return profileDTO.getLastName();
    }

    @Override
    public InputStream newPictureStream() {
        var bytes = Base64.getDecoder().decode(profileDTO.getProfilePicture());
        return new ByteArrayInputStream(bytes);
    }
}
