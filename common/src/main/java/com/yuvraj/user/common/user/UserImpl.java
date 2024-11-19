package com.yuvraj.user.common.user;

import lombok.RequiredArgsConstructor;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class UserImpl implements User {
    private final UserData userData;

    @Override
    public String userId() {
        return userData.getUserId();
    }

    @Override
    public String email() {
        return userData.getEmailId();
    }

    @Override
    public PhoneNumber phoneNumber() {
        return userData.getPhoneNumber();
    }

    @Override
    public String apiKey() {
        return userData.getApiKey();
    }
}
