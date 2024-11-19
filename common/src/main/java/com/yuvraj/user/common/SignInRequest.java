package com.yuvraj.user.common;

import com.yuvraj.user.common.signup.Device;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yuvraj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest {
    private String userId;
    private String app;
    private Device device;
}
