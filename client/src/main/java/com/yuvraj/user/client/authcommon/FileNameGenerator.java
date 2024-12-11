package com.yuvraj.user.client.authcommon;

/**
 * @author Yuvraj Singh
 */
public class FileNameGenerator {
    public String user(String userId) {
        return generate(userId, "u");
    }

    public String profile(String userId) {
        return generate(userId, "p");
    }

    public String profilePicture(String userId) {
        return generate(userId, "pp");
    }

    private String generate(String userId, String suffix) {
        return userId + "_" + suffix;
    }
}
