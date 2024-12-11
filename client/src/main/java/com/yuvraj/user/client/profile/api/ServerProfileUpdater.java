package com.yuvraj.user.client.profile.api;

import com.yuvraj.user.client.profile.Profile;
import com.yuvraj.user.common.profile.ProfileDTO;
import com.yuvraj.util.streams.Streams;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.CompletableFuture;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class ServerProfileUpdater {
    private final String baseURL;
    private final String token;

    public CompletableFuture<Void> update(Profile profile) {
        ProfileDTO profileDTO = parseProfile(profile);
        var profileClient = new ProfileClient(baseURL, token);
        return profileClient.updateProfile(profileDTO);
    }

    private ProfileDTO parseProfile(Profile profile) {
        var profileDTO = new ProfileDTO();
        profileDTO.setFirstName(profile.firstName());
        profileDTO.setLastName(profile.lastName());
        var inputStream = profile.newPictureStream();
        if(inputStream != null) {
            try(inputStream){
                byte[] bytes = Streams.toByteArray(inputStream);
                var b64 = Base64.getEncoder().encodeToString(bytes);
                profileDTO.setProfilePicture(b64);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return profileDTO;
    }
}
