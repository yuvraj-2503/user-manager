package com.yuvraj.user.common.signup;

import com.yuvraj.user.common.user.PhoneNumber;
import lombok.Data;

/**
 * @author Yuvraj
 */
@Data
public class SignUpRequest {
    private String emailId;
    private PhoneNumber phoneNumber;
    private int otp;
    private String sessionId;
    private Device device;
    private String app;
}
