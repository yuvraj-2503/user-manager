package com.yuvraj.filestore.file;

import com.yuvraj.filestore.common.FileIOException;
import com.yuvraj.util.json.Json;
import lombok.RequiredArgsConstructor;

/**
 * @author Yuvraj
 */
@RequiredArgsConstructor
public class JsonFile<T> implements DataFile<T> {

    private final File file;
    private final Class<T> type;
    private final Json json = Json.create();

    @Override
    public void write(T data) {
        String encoded = json.encode(data);
        try(var stream = file.write()){
            stream.write(encoded.getBytes());
        }catch (Exception e) {
            throw new FileIOException("Failed to write data to file", e);
        }
    }

    @Override
    public T read() {
        try (var stream = file.read()) {
            byte[] b = stream.readAllBytes();
            var j = new String(b);
            return json.decode(j, type);
        }catch (Exception e) {
            throw new FileIOException("Failed to read data from file", e);
        }
    }
}
