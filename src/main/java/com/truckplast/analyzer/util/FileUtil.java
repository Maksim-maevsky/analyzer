package com.truckplast.analyzer.util;


import com.truckplast.analyzer.exeption_handler.exception.WriteByteArrayToFileExeption;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public final class FileUtil {

    private FileUtil() {
    }

    public static File getFile(String filePath, String fileName, byte[] fileBytes) {

        try {

            File file = new File(filePath + fileName);
            FileUtils.writeByteArrayToFile(file, fileBytes);

            return file;

        } catch (IOException exception) {

            throw new WriteByteArrayToFileExeption("Exception while writing byte array to file " + fileName);

        }
    }

    public static void tryDeleteFile(File file) {

        try {

            FileUtils.forceDelete(file);

        } catch (IOException exception) {

            exception.printStackTrace();

        }
    }
}
