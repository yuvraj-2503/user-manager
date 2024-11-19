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
public class UserData {
    private String userId;
    private String emailId;
    private PhoneNumber phoneNumber;
    private String apiKey;
}
