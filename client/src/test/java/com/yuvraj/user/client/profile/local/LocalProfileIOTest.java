package com.yuvraj.user.client.profile.local;

import com.yuvraj.filestore.file.PlainFile;
import com.yuvraj.user.client.profile.ProfileImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

class LocalProfileIOTest {

    private LocalProfileIO localProfileIO;

    @BeforeEach
    void setUp() throws IOException {
        var path = Path.of(System.getProperty("user.home"), "profiles", "local");
        Files.createDirectories(path);
        localProfileIO = new LocalProfileIOImpl("674e06cfffd88e7ae8835f5c", path);
    }

    @AfterEach
    void tearDown() {
        localProfileIO = null;
    }

    @Test
    void getProfile() throws IOException {
        var profile = localProfileIO.getProfile();
        System.out.println(profile.firstName());
        System.out.println(profile.lastName());
        System.out.println(Arrays.toString(profile.newPictureStream().readAllBytes()));
    }

    @Test
    void updateProfile() {
        var instant = localProfileIO.updateProfile(new ProfileImpl("Yuvraj", "Singh",
                                        new byte[]{0}));
        System.out.println(instant.toString());
    }
}