package com.yuvraj.user.common.signup;

import lombok.Data;

/**
 * @author Yuvraj
 */
@Data
public class Device {
    private final String serialNumber;
    private final String name;
    private final String os;
    private final String fingerPrint;
    private final DeviceType deviceType;
}
