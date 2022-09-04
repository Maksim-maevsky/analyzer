package com.truckplast.analyzer.service.file.impl;

import com.truckplast.analyzer.entity.FileInfo;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.util.DecoderUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.internet.MimeBodyPart;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class FileInfoServiceImpl implements FileInfoService {

    private final DecoderUtil decoderUtil;


    @SneakyThrows
    @Override
    public List<FileInfo> iterateMimeBodyParts(Multipart multipart) {

        int countOfAttachments = 0;
        int countOfBodyParts = multipart.getCount();
        List<FileInfo> fileInfoList = new ArrayList<>();

        for (int partCount = 0; partCount < countOfBodyParts; partCount++) {

            MimeBodyPart part = (MimeBodyPart) multipart.getBodyPart(partCount);

            if (isAttachment(part)) {

                countOfAttachments++;

                log.info("Try to save file");

                FileInfo fileInfo = getFileInfo(part);
                fileInfoList.add(fileInfo);

            }
        }

        isAttachmentsToMessages(countOfAttachments);

        return fileInfoList;
    }

    private boolean isAttachment(MimeBodyPart part) throws MessagingException {

        return Part.ATTACHMENT.equalsIgnoreCase(part.getDisposition());
    }

    private FileInfo getFileInfo(MimeBodyPart part) throws IOException, MessagingException {

        String fullEncodeFileName = part.getFileName();
        String fullDecodeFileName = decoderUtil.getDecodedString(fullEncodeFileName);
        String fileName = FilenameUtils.getBaseName(fullDecodeFileName);

        FileInfo fileInfo = new FileInfo();
        fileInfo.setExtension(FilenameUtils.getExtension(fullDecodeFileName));
        fileInfo.setFileName(fileName);
        byte[] bytes = IOUtils.toByteArray(part.getInputStream());

        fileInfo.setFileBytes(bytes);

        return fileInfo;
    }

    private void isAttachmentsToMessages(int countOfAttachments) {

        if(countOfAttachments == 0){

            log.info("Messages were without attachments.");

        }else{

            log.info("All attachments were add to list");
        }
    }
}
