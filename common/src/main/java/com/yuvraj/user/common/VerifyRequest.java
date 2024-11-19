package com.yuvraj.user.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yuvraj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyRequest {
    private String sessionId;
    private int otp;
}
