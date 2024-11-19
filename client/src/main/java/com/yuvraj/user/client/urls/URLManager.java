package com.yuvraj.user.client.urls;

import com.yuvraj.filestore.BlockManager;
import com.yuvraj.filestore.file.JsonFile;
import com.yuvraj.filestore.file.PlainFile;
import com.yuvraj.util.json.JsonException;
import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

import java.nio.file.Path;
import java.util.Map;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
//@Slf4j
public class URLManager {

    private static final String fileName = "urls";
    private final BlockManager blockManager;
    private final String locatorUrl;

    public URLManager(String locatorUrl, Path path) {
        this.locatorUrl = locatorUrl;
        blockManager = new BlockManager(path);
    }

    public Map<String, String> getUrl() {
        if (blockManager.exists(fileName)) {
            try {
                return getFile().read();
            } catch (JsonException e) {
                blockManager.delete(fileName);
                return create();
            }
        }
        return create();
    }

    private Map<String, String> create() {
        Map<String, String> urls = new ServiceLocatorClient(locatorUrl).get().join();
        blockManager.create(fileName);
        JsonFile<Map> file = getFile();
        file.write(urls);
        return urls;
    }

    public Map<String, String> sync() {
        Map<String, String> urls = new ServiceLocatorClient(locatorUrl).get().join();
        JsonFile<Map> file = getFile();
        file.write(urls);
        return urls;
    }

    private JsonFile<Map> getFile() {
        var plainFile = new PlainFile(blockManager, fileName);
        return new JsonFile<>(plainFile, Map.class);
    }

}
