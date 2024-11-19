package com.yuvraj.user.common.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yuvraj
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {
    private String countryCode;
    private String number;
}
