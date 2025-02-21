package com.yuvraj.filestore.common;

/**
 * @author Yuvraj
 */
public class FileIOException extends RuntimeException {
    public FileIOException(String message) {
        super(message);
    }

    public FileIOException(String message, Throwable cause) {
        super(message, cause);
    }
}
