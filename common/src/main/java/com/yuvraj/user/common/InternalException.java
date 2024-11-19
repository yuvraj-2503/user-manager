package com.yuvraj.user.common;

/**
 * @author Yuvraj
 */
public class InternalException extends RuntimeException {
    public InternalException(String message) {
        super(message);
    }

    public InternalException(String message, Throwable cause) {
        super(message, cause);
    }
}
