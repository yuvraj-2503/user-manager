package com.yuvraj.user.common.signup;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author Yuvraj
 */
public class OTPException extends RuntimeException {

    @Getter
    private final OTPStatus otpStatus;

    public OTPException(OTPStatus otpStatus) {
        super(otpStatus.toString());
        this.otpStatus = otpStatus;
    }
}
