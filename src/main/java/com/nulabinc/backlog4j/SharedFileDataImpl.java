package com.nulabinc.backlog4j;

import java.io.InputStream;

/**
 * Created by baba on 2014/07/02.
 */
public class SharedFileDataImpl implements SharedFileData {

    private final String filename;

    private final InputStream content;

    public SharedFileDataImpl(String filename, InputStream content) {
        this.filename = filename;
        this.content = content;
    }

    @Override
    public String getFilename() {
        return filename;
    }

    @Override
    public InputStream getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SharedFileDataImpl{" +
                "filename='" + filename + '\'' +
                ", content=" + content +
                '}';
    }

}
