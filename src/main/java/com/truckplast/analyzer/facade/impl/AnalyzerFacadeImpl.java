package com.truckplast.analyzer.facade.impl;

import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.facade.AnalyzerFacade;
import com.truckplast.analyzer.pojo.MailNotificationInfo;
import com.truckplast.analyzer.pojo.RefillRequest;
import com.truckplast.analyzer.pojo.RefillResponse;
import com.truckplast.analyzer.pojo.RefillResult;
import com.truckplast.analyzer.service.PartService;
import com.truckplast.analyzer.service.analysis.PartAnalyzerService;
import com.truckplast.analyzer.service.file.FileCreatorService;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.mail.MailNotificationService;
import com.truckplast.analyzer.service.mail.MailPreparerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Data
@RequiredArgsConstructor
public class AnalyzerFacadeImpl implements AnalyzerFacade {

    private final PartAnalyzerService partAnalyzerService;
    private final FileInfoService fileInfoService;
    private final FileCreatorService fileCreatorService;
    private final PartService partService;
    private final MailNotificationService mailNotificationService;
    private final MailPreparerService mailPreparerService;

    @Override
    public void differenceBetweenWarehouses(RefillRequest refillRequest) {

        RefillResponse refillResponse = partService.getRefilledInfo(refillRequest);

        RefillResult refillResult = partAnalyzerService.getRefillPartStorageInfo(refillResponse);
        FileInfoDto fileInfoDto = fileCreatorService.getFile(refillResult);
        refillResult.setFileInfoDto(fileInfoDto);

        MailNotificationInfo mailNotificationInfo = mailPreparerService.refillPartStoragePreparation(refillResult);
        mailNotificationService.send(mailNotificationInfo);

    }
}
