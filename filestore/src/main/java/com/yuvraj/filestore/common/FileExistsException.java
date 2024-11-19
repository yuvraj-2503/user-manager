package com.yuvraj.filestore.common;

/**
 * @author Yuvraj
 */
public class FileExistsException extends RuntimeException {
    public FileExistsException(String message) {
        super(message);
    }
}
