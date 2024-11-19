package com.yuvraj.user.common.signup;

import com.yuvraj.user.common.user.PhoneNumber;
import lombok.Data;

/**
 * @author Yuvraj
 */
@Data
public class AuthenticatedUser {
    private String userId;
    private String emailId;
    private PhoneNumber phoneNumber;
    private String apiKey;
}
