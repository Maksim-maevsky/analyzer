package com.truckplast.analyzer.service.mail.impl;


import com.truckplast.analyzer.constant.MailConstant;
import com.truckplast.analyzer.entity.part.PartInfo;
import com.truckplast.analyzer.pojo.MailNotificationInfo;
import com.truckplast.analyzer.pojo.RefillResult;
import com.truckplast.analyzer.service.mail.MailPreparerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailPreparerServiceImpl implements MailPreparerService {

    @Value("${spring.mail.username}")
    private String fromMail;

    @Override
    public MailNotificationInfo refillPartStoragePreparation(RefillResult refillResult) {

        log.info("Try to get MailNotificationInfo.");

        MailNotificationInfo mailNotificationInfo = new MailNotificationInfo();
        mailNotificationInfo.setFrom(fromMail);

        String targetStorage = getPartStorageString(refillResult.getTargetPartStorageName());
        String currentStorage = getPartStorageString(refillResult.getCurrentPartStorageName());
        String message = "see attachment";
        String topic = String.format(MailConstant.REFILLING_MESSAGE_TOPIC, targetStorage, currentStorage);

        mailNotificationInfo.setSubject(topic);
        mailNotificationInfo.setTo("mm@opox.ru");
        mailNotificationInfo.setMessage(message);
        mailNotificationInfo.setFileInfoDto(refillResult.getFileInfoDto());

        return mailNotificationInfo;
    }

    private String getPartStorageString(Set<String> storageNames) {

        StringBuilder storage = new StringBuilder();

        storageNames.parallelStream().forEach(s -> storage.append(s + " "));

        return storage.toString();
    }
}
