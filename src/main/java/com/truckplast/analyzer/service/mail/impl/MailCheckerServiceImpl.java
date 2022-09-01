package com.truckplast.analyzer.service.mail.impl;


import com.truckplast.analyzer.entity.FileInfo;
import com.truckplast.analyzer.entity.MailInfo;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.mail.MailCheckerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeUtility;
import javax.mail.search.FlagTerm;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailCheckerServiceImpl implements MailCheckerService {

    private final FlagTerm flagTerm;

    private final Folder folder;

    private final FileInfoService fileInfoService;

    @Value("${javax.mail.user}")
    private String user;

    @Value("${javax.mail.password}")
    private String password;

    @Value("${javax.mail.host}")
    private String host;

    @Value("${javax.mail.folder}")
    private String folderName;


    @Override
    @SneakyThrows
    public List<MailInfo> checkEmail() {

        log.info("Try to get messages");

        Message messages[] = folder.search(flagTerm);
        List<Message> messageList = Arrays.asList(messages);
        isMessages(messageList);
        List<MailInfo> mailInfos = getMailInfos(messageList);

        folder.close();

        return mailInfos;
    }

    private boolean isMessages(List<Message> messageList) {

        if (messageList.isEmpty()) {

            log.info("There weren't new messages.");

            return true;
        }

        return false;
    }

    @SneakyThrows
    private List<MailInfo> getMailInfos(List<Message> messageList) {

        List<MailInfo> mailList = new ArrayList<>();

        messageList.forEach((message) -> mailList.add(getMailInfoAndSetAttachment(message)));


        return mailList;
    }

    private MailInfo getMailInfoAndSetAttachment(Message message) {

        Multipart multipart;
        MailInfo mail = new MailInfo();

        try {

            multipart = (Multipart) message.getContent();
            mail.setSubject(message.getSubject());
            getFromAndSetToMailInfo(message, mail);
            List<FileInfo> fileInfoList = fileInfoService.iterateMimeBodyParts(multipart);
            mail.setFileInfoList(fileInfoList);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return mail;
    }

    private void getFromAndSetToMailInfo(Message message, MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException {

        Address address = message.getFrom()[0];
        String from = MimeUtility.decodeText(address.toString());
        mailInfo.setFrom(from);

    }
}

