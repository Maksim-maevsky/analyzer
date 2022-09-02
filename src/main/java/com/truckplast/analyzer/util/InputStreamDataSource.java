package com.truckplast.analyzer.util;

import lombok.Data;
import org.apache.commons.io.IOUtils;

import javax.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Data
public class InputStreamDataSource implements DataSource {

    String contentType;
    String name;
    byte[] fileData;

    public InputStreamDataSource(String contentType, String name, InputStream inputStream) throws IOException {
        this.contentType = contentType;
        this.name = name;
        fileData = IOUtils.toByteArray(inputStream);
    }

    public InputStream getInputStream() {
        return new ByteArrayInputStream(fileData);
    }

    public OutputStream getOutputStream() {
        throw new UnsupportedOperationException("Not implemented");
    }
}
