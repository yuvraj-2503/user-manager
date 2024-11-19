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
public class VerifyResponse {
    private String userId;
    private String token;
}
