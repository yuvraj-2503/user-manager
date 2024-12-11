package com.yuvraj.user.client.localuser;

import com.yuvraj.filestore.BlockManager;
import com.yuvraj.filestore.PlainFileManager;
import com.yuvraj.user.client.authcommon.FileNameGenerator;
import com.yuvraj.user.common.user.UserData;

import java.nio.file.Path;

/**
 * @author Yuvraj
 */
public class LocalUserManager {
    private final PlainFileManager plainFileManager;

    private final FileNameGenerator fileNameGenerator = new FileNameGenerator();

    public LocalUserManager(Path path) {
        this.plainFileManager = new PlainFileManager(new BlockManager(path));
    }

    public void create(UserData userData) {
        var plainFile = plainFileManager.create(fileNameGenerator.user(userData.getUserId()));
        var userDataFile = new UserDataFile(plainFile);
        userDataFile.write(userData);
    }

    public void update(UserData userData) {
        var file = plainFileManager.get(fileNameGenerator.user(userData.getUserId()));
        var userDataFile = new UserDataFile(file);
        userDataFile.write(userData);
    }

    public void delete(String userId) {
        plainFileManager.delete(fileNameGenerator.user(userId));
    }

    public boolean exists(String userId) {
        return plainFileManager.exists(fileNameGenerator.user(userId));
    }

    public UserData get(String userId) {
        var file = plainFileManager.get(fileNameGenerator.user(userId));
        var userDataFile = new UserDataFile(file);
        return userDataFile.read();
    }
}
