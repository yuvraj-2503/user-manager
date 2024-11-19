package com.yuvraj.filestore;

import com.yuvraj.filestore.common.FileExistsException;
import com.yuvraj.filestore.common.FileIOException;
import com.yuvraj.filestore.common.FileNotFoundException;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class BlockManager {
    private final Path blockDirectory;

    public InputStream read(String resourceId) {
        Path filePath = getFilePath(resourceId);
        try {
            return Files.newInputStream(filePath);
        } catch (Exception e) {
            throw new FileIOException("Could not read block for resource " + resourceId, e);
        }
    }

    private Path getFilePath(String resourceId) {
        Path filePath = blockDirectory.resolve(resourceId);
        if (!Files.exists(filePath)) {
            throw new FileNotFoundException("Block file not found for resource " + resourceId);
        }
        return filePath;
    }

    public OutputStream write(String resourceId) {
        Path filePath = getFilePath(resourceId);
        try {
            return Files.newOutputStream(filePath);
        } catch (Exception e) {
            throw new FileIOException("Could not write block for resource " + resourceId, e);
        }
    }

    public void create(String resourceId) {
        try {
            Files.createFile(blockDirectory.resolve(resourceId));
        } catch (FileAlreadyExistsException e) {
            throw new FileExistsException("Resource block already exists for " + resourceId);
        } catch (Exception e) {
            throw new FileIOException("Failed to create block for resource " + resourceId, e);
        }
    }

    public boolean delete(String resourceId) {
        try {
            Files.delete(blockDirectory.resolve(resourceId));
            return true;
        } catch (NoSuchFileException e) {
            return false;
        } catch (Exception e) {
            throw new FileIOException("Failed to delete block for resource " + resourceId, e);
        }
    }

    public boolean exists(String resourceId) {
        return Files.exists(blockDirectory.resolve(resourceId));
    }
}
