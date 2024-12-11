package com.yuvraj.user.client.profile.local;

import com.yuvraj.filestore.BlockManager;
import com.yuvraj.filestore.PlainFileManager;
import com.yuvraj.filestore.file.File;
import com.yuvraj.filestore.file.JsonFile;
import com.yuvraj.user.client.authcommon.FileNameGenerator;
import com.yuvraj.user.common.profile.ProfileMetadata;

import java.nio.file.Path;

/**
 * @author Yuvraj Singh
 */
public class ProfileFileManager {
    private final FileNameGenerator fileNameGenerator = new FileNameGenerator();
    private final PlainFileManager plainFileManager;
    private final String userId;

    public ProfileFileManager(Path blockPath, String userId) {
        this.userId = userId;
        var blockManager = new BlockManager(blockPath);
        this.plainFileManager = new PlainFileManager(blockManager);
    }

    public JsonFile<ProfileMetadata> create() {
        String filename = fileNameGenerator.profile(userId);
        var file = plainFileManager.create(filename);
        return new JsonFile<>(file, ProfileMetadata.class);
    }

    public JsonFile<ProfileMetadata> get() {
        String filename = fileNameGenerator.profile(userId);
        if (!plainFileManager.exists(filename)) {
            return null;
        }

        var file = plainFileManager.get(filename);
        return new JsonFile<>(file, ProfileMetadata.class);
    }

    public File getPicture() {
        String fileName = fileNameGenerator.profilePicture(userId);
        if (!plainFileManager.exists(fileName)) {
            return null;
        }
        return plainFileManager.get(fileName);
    }

    public File createPicture() {
        String fileName = fileNameGenerator.profilePicture(userId);
        return plainFileManager.create(fileName);
    }

}
