package com.yuvraj.user.common.profile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Yuvraj Singh
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDTO {
    private String firstName;
    private String lastName;
    private String profilePicture;
}
