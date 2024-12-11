package com.yuvraj.user.client.profile.api;

import com.yuvraj.user.common.profile.ProfileDTO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

/**
 * @author Yuvraj Singh
 */
class ProfileClientTest {

    private ProfileClient profileClient;

    @BeforeEach
    void setUp() {
        var token = "ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmhjSEFpT2lKVFQwTkpRVXdpTENKaGRYUm9iM0pwZW1Wa0lqcDBjblZsTENKbGJXRnBiQ0k2SW5OcGJtZG9MbmwxZG5KaGFqRXdORGRBWjIxaGFXd3VZMjl0SWl3aVpYaHdJam94TnpNek56Y3hOVGs1TENKcFlYUWlPakUzTXpNeE5qWTNPVGtzSW1wMGFTSTZJbE5QUTBsQlRDSXNJbXRwYm1RaU9pSlZVMFZTSWl3aWJXRmphR2x1WlNJNklrWkpUa2RGVWxCU1NVNVVYMGhCVTBoZk1USXpJaXdpYldGamFHbHVaVjlwWkNJNklrWkpUa2RGVWxCU1NVNVVYMGhCVTBoZk1USXpJaXdpYzNWaUlqb2lJaXdpZFhObGNpSTZJalkzTkdVd05tTm1abVprT0RobE4yRmxPRGd6TldZMVl5SXNJblZ6WlhKSlpDSTZJalkzTkdVd05tTm1abVprT0RobE4yRmxPRGd6TldZMVl5SjkuYTNOSS1Wajk0T2YtZmNuZnJtUEFOa0ZtQ3E5SlVfTlZkZlFlTC1RUUhTalNVOURTazhrdF82RWpXNm1lWE5VZ25MQjBySlJsV3g0MlRqOEllUVRlaEE";
        this.profileClient = new ProfileClient("http://localhost:8080/api/v1", token);
    }

    @AfterEach
    void tearDown() {
        this.profileClient = null;
    }

    @Test
    void updateProfile() {
        profileClient.updateProfile(new ProfileDTO("Yuvraj", "Singh",
                "base64profilePic")).join();
    }

    @Test
    void getProfile() {
        var profile = profileClient.getProfile(Instant.ofEpochMilli(0)).join();
        System.out.println(profile.getFirstName());
        System.out.println(profile.getLastName());
        System.out.println(profile.getProfilePicture());
    }
}