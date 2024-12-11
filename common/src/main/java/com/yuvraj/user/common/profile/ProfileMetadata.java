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
public class ProfileMetadata {
    private String firstName;
    private String lastName;
    private long updatedOn;
}
