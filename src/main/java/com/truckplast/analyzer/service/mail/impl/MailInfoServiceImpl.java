package com.truckplast.analyzer.service.mail.impl;


import com.truckplast.analyzer.entity.MailInfo;
import com.truckplast.analyzer.repository.MailInfoRepository;
import com.truckplast.analyzer.service.mail.MailInfoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailInfoServiceImpl implements MailInfoService {

    private final MailInfoRepository mailInfoRepository;

    @Override
    public void setLocalDateTimeAndId(List<MailInfo> mailInfoList) {

        log.info("Set date and Id to mailInfoList.");

        mailInfoList.forEach(x -> {
            x.setDateTime(LocalDateTime.now());
            x.setId(UUID.randomUUID());
        });
    }


    public void saveAll( List<MailInfo> mailInfoList){

        log.info("Save mailInfoList.");

        mailInfoRepository.saveAll(mailInfoList);

    }
}
