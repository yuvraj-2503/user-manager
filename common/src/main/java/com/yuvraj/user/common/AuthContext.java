package com.yuvraj.user.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.nio.file.Path;
import java.util.Map;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
@Getter
public class AuthContext {
    private final Path blockPath;
    private final AuthConfig authConfig;
    private final Map<String, String> urls;
    private final App app;

    public String userUrl() {
        return urls.get(ServerKeys.USER_SERVER);
    }
}
