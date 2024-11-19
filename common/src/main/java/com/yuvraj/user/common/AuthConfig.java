package com.yuvraj.user.common;

import com.yuvraj.user.common.signup.Device;
import lombok.Data;

/**
* @author Yuvraj 
*/
@Data
public class AuthConfig {
    private final App app;
    private final Device device;
    private final String locatorUrl;
}
