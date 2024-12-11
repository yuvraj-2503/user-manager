package com.yuvraj.user.client.profile;

import lombok.RequiredArgsConstructor;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/**
 * @author Yuvraj Singh
 */
@RequiredArgsConstructor
public class ProfileImpl implements Profile {
    private final String firstName;
    private final String lastName;
    private final byte[] data;

    @Override
    public String firstName() {
        return firstName;
    }

    @Override
    public String lastName() {
        return lastName;
    }

    @Override
    public InputStream newPictureStream() {
        return new ByteArrayInputStream(data);
    }
}
