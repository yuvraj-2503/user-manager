package com.yuvraj.user.client.profile;

import java.io.InputStream;

/**
 * @author Yuvraj Singh
 */
public interface Profile {
    String firstName();

    String lastName();

    InputStream newPictureStream();
}