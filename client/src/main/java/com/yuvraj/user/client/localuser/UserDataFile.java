package com.yuvraj.user.client.localuser;

import com.yuvraj.filestore.file.DataFile;
import com.yuvraj.filestore.file.File;
import com.yuvraj.filestore.file.JsonFile;
import com.yuvraj.user.common.user.UserData;

/**
 * @author Yuvraj
 */
public class UserDataFile implements DataFile<UserData> {
    private final JsonFile<UserData> file;

    public UserDataFile(File file) {
        this.file = new JsonFile<>(file, UserData.class);
    }

    @Override
    public void write(UserData data) {
        file.write(data);
    }

    @Override
    public UserData read() {
        return file.read();
    }
}
