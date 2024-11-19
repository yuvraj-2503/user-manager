package com.yuvraj.user.common;

import com.yuvraj.user.common.user.PhoneNumber;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Yuvraj
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Contact {
    private String emailId;
    private PhoneNumber phoneNumber;

    public static Contact email(String email) {
        return new Contact(email, null);
    }

    public static Contact phone(PhoneNumber phoneNumber) {
        return new Contact(null, phoneNumber);
    }
}
