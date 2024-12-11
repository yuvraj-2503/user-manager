package com.yuvraj.user.client.profile.local;

import com.yuvraj.user.client.profile.Profile;

import java.time.Instant;

/**
 * @author Yuvraj Singh
 */
public interface LocalProfileIO {
    LocalProfile getProfile();
    Instant updateProfile(Profile profile);
}
