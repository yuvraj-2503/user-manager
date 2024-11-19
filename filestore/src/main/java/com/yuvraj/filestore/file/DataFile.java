package com.yuvraj.filestore.file;

/**
 * @author Yuvraj
 */
public interface DataFile<T> {
    void write(T data);

    T read();
}
