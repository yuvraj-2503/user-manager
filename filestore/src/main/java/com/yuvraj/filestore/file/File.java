package com.yuvraj.filestore.file;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yuvraj
 */
public interface File {
    InputStream read();
    OutputStream write();
}
