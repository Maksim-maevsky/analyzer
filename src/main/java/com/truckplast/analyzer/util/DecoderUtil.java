package com.truckplast.analyzer.util;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;

import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;

@Component
public class DecoderUtil {

    public String getDecodedString(String fileName) throws UnsupportedEncodingException {

        return MimeUtility.decodeText(FilenameUtils.getBaseName(fileName));
    }
}
