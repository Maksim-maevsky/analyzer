package com.truckplast.analyzer.service.mail.impl;


import com.truckplast.analyzer.entity.FileInfo;
import com.truckplast.analyzer.entity.MailInfo;
import com.truckplast.analyzer.repository.MailInfoRepository;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.mail.MailInfoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeUtility;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailInfoServiceImpl implements MailInfoService {

    private final MailInfoRepository mailInfoRepository;

    private final FileInfoService fileInfoService;


    @Override
    @SneakyThrows
    public List<MailInfo> getMailInfos(List<Message> messageList) {

        List<MailInfo> mailList = new ArrayList<>();
        messageList.parallelStream().forEach(message -> mailList.add(getMailInfoAndSetAttachment(message)));

        return mailList;
    }

    @Override
    @Transactional
    public void saveAll(List<MailInfo> mailInfoList) {

        log.info("Save mailInfoList.");

        mailInfoRepository.saveAll(mailInfoList);

    }

    private MailInfo getMailInfoAndSetAttachment(Message message) {

        Multipart multipart;
        MailInfo mailInfo = new MailInfo();

        try {

            mailInfo.setSubject(message.getSubject());
            setLocalDateTimeAndId(mailInfo);
            getFromAndSetToMailInfo(message, mailInfo);
            multipart = (Multipart) message.getContent();
            mailInfo.setMultipart(multipart);

        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        return mailInfo;
    }

    private void getFromAndSetToMailInfo(Message message, MailInfo mailInfo) throws MessagingException, UnsupportedEncodingException {

        Address address = message.getFrom()[0];
        String from = MimeUtility.decodeText(address.toString());
        mailInfo.setFrom(from);

    }

    private void setLocalDateTimeAndId(MailInfo mailInfo) {

        log.info("Set date and Id to mailInfoList.");

        mailInfo.setDateTime(LocalDateTime.now());
        mailInfo.setId(UUID.randomUUID());

    }
}
