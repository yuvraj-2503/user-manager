package com.yuvraj.user.client.localuser;

import com.yuvraj.user.common.user.PhoneNumber;
import com.yuvraj.user.common.user.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class LocalUserManagerTest {
    private LocalUserManager localUserManager;

    @BeforeEach
    void setUp() {
        localUserManager = new LocalUserManager(Path.of(System.getProperty("user.home")));
    }

    @AfterEach
    void tearDown() {
        localUserManager = null;
    }

    @Test
    void create() {
        localUserManager.create(new UserData("6719252c58da11805939fea3",
                "singh.yuvraj1047@gmail.com",
                new PhoneNumber("+91", "7250378940"),
                "ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmhjSEFpT2lKVFQwTkpRVXdpTENKaGRYUm9iM0pwZW1Wa0lqcDBjblZsTENKbGJXRnBiQ0k2SW5OcGJtZG9MbmwxZG5KaGFqRXdORGRBWjIxaGFXd3VZMjl0SWl3aVpYaHdJam94TnpNd016QTFPVFkwTENKcFlYUWlPakUzTWprM01ERXhOalFzSW1wMGFTSTZJbE5QUTBsQlRDSXNJbXRwYm1RaU9pSlZVMFZTSWl3aWJXRmphR2x1WlNJNklrWkpUa2RGVWxCU1NVNVVYMGhCVTBoZk1USXpJaXdpYldGamFHbHVaVjlwWkNJNklrWkpUa2RGVWxCU1NVNVVYMGhCVTBoZk1USXpJaXdpYzNWaUlqb2lJaXdpZFhObGNpSTZJalkzTVRreU5USmpOVGhrWVRFeE9EQTFPVE01Wm1WaE15SXNJblZ6WlhKSlpDSTZJalkzTVRreU5USmpOVGhrWVRFeE9EQTFPVE01Wm1WaE15SjkuNGRHSWhlZlZWOHc2VVIzRlZMUXNKTTAzR1RMY3BUYTE5c3JGWFoteE5iV29zd0dZUzlmQjBIb1ZmeXEwX0l2Um9rbkhDSll0T29vZ0h0cWlJSmNhNGc"));
    }

    @Test
    void update() {
        localUserManager.update(new UserData("6719252c58da11805939fea3",
                "singh.yuvraj1047@gmail.com",
                new PhoneNumber("+91", "7250378941"),
                "ZXlKaGJHY2lPaUpJVXpVeE1pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SmhjSEFpT2lKVFQwTkpRVXdpTENKaGRYUm9iM0pwZW1Wa0lqcDBjblZsTENKbGJXRnBiQ0k2SW5OcGJtZG9MbmwxZG5KaGFqRXdORGRBWjIxaGFXd3VZMjl0SWl3aVpYaHdJam94TnpNd016QTFPVFkwTENKcFlYUWlPakUzTWprM01ERXhOalFzSW1wMGFTSTZJbE5QUTBsQlRDSXNJbXRwYm1RaU9pSlZVMFZTSWl3aWJXRmphR2x1WlNJNklrWkpUa2RGVWxCU1NVNVVYMGhCVTBoZk1USXpJaXdpYldGamFHbHVaVjlwWkNJNklrWkpUa2RGVWxCU1NVNVVYMGhCVTBoZk1USXpJaXdpYzNWaUlqb2lJaXdpZFhObGNpSTZJalkzTVRreU5USmpOVGhrWVRFeE9EQTFPVE01Wm1WaE15SXNJblZ6WlhKSlpDSTZJalkzTVRreU5USmpOVGhrWVRFeE9EQTFPVE01Wm1WaE15SjkuNGRHSWhlZlZWOHc2VVIzRlZMUXNKTTAzR1RMY3BUYTE5c3JGWFoteE5iV29zd0dZUzlmQjBIb1ZmeXEwX0l2Um9rbkhDSll0T29vZ0h0cWlJSmNhNGc"));
    }

    @Test
    void delete() {
        localUserManager.delete("6719252c58da11805939fea3");
    }

    @Test
    void exists() {
        System.out.println(localUserManager.exists("6719252c58da11805939fea3"));
    }

    @Test
    void get() {
        System.out.println(localUserManager.get("6719252c58da11805939fea3"));
    }
}