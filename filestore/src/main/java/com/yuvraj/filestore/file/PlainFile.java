package com.yuvraj.filestore.file;

import com.yuvraj.filestore.BlockManager;
import lombok.RequiredArgsConstructor;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class PlainFile implements File {
    private final BlockManager blockManager;
    private final String resourceId;

    @Override
    public InputStream read() {
        return blockManager.read(resourceId);
    }

    @Override
    public OutputStream write() {
        return blockManager.write(resourceId);
    }
}
