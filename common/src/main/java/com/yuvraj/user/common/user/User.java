package com.yuvraj.user.common.user;

/**
 * @author Yuvraj
 */
public interface User {
    String userId();

    String email();

    PhoneNumber phoneNumber();

    String apiKey();
}
