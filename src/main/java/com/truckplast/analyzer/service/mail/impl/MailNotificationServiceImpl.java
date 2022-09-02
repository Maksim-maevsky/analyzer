package com.truckplast.analyzer.service.mail.impl;


import com.truckplast.analyzer.pojo.MailNotificationInfo;
import com.truckplast.analyzer.service.mail.MailNotificationService;
import com.truckplast.analyzer.util.MimeMessageCreator;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Data
@Slf4j
@RequiredArgsConstructor
@Service
public class MailNotificationServiceImpl implements MailNotificationService {

    private final JavaMailSender emailSender;


    @SneakyThrows
    @Override
    public void send(MailNotificationInfo mailNotificationInfo) {

        log.info("Try to send message.");

        MimeMessage mimeMessage = MimeMessageCreator.getMimeMessage(mailNotificationInfo);

        emailSender.send(mimeMessage);

        log.info("Message was sent");

    }
}
