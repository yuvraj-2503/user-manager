package com.yuvraj.filestore;

import com.yuvraj.filestore.common.FileNotFoundException;
import com.yuvraj.filestore.file.File;
import com.yuvraj.filestore.file.PlainFile;

/**
 * @author Yuvraj
 */
public class PlainFileManager {
    private final BlockManager blockManager;

    public PlainFileManager(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    public File create(String resourceId) {
        blockManager.create(resourceId);
        return new PlainFile(blockManager, resourceId);
    }

    public boolean exists(String resourceId) {
        return blockManager.exists(resourceId);
    }

    public File get(String resourceId) {
        if (exists(resourceId)) {
            return new PlainFile(blockManager, resourceId);
        }
        throw new FileNotFoundException("File not found: " + resourceId);
    }

    public void delete(String resourceId) {
        if (exists(resourceId)) {
            blockManager.delete(resourceId);
        }
        throw new FileNotFoundException("File not found: " + resourceId);
    }
}
