package com.truckplast.analyzer.service.file;

import com.truckplast.analyzer.entity.FileInfo;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import java.io.IOException;
import java.util.List;

public interface FileInfoService {

    List<FileInfo> iterateMimeBodyParts(Multipart multipart) throws MessagingException, IOException;
}
