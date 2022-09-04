package com.truckplast.analyzer.facade.impl;

import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.dto.pojo.RefillRequestDto;
import com.truckplast.analyzer.dto.pojo.RefillResponseDto;
import com.truckplast.analyzer.dto.pojo.RefillResultDto;
import com.truckplast.analyzer.facade.AnalyzerFacade;
import com.truckplast.analyzer.pojo.MailNotificationInfo;
import com.truckplast.analyzer.service.PartService;
import com.truckplast.analyzer.service.analysis.PartAnalyzerService;
import com.truckplast.analyzer.service.file.FileCreatorService;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.mail.MailNotificationService;
import com.truckplast.analyzer.service.mail.MailPreparerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

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
    public void differenceBetweenWarehouses() {

        Set<String> target = Set.of("PLASTIC", "TANGDE");
        Set<String> current = Set.of("MIKHNEVO");

        RefillRequestDto refillRequestDto = new RefillRequestDto();
        refillRequestDto.setCurrentPartStorageNameSet(current);
        refillRequestDto.setTargetPartStorageNameSet(target);
        RefillResponseDto refillResponseDto = partService.getRefilledInfo(refillRequestDto);
        RefillResultDto refillResultDto = partAnalyzerService.getRefillPartStorageInfo(refillResponseDto);
        FileInfoDto fileInfoDto = fileCreatorService.getFile(refillResultDto);
        refillResultDto.setFileInfoDto(fileInfoDto);
        MailNotificationInfo mailNotificationInfo = mailPreparerService.refillPartStoragePreparation(refillResultDto);
        mailNotificationService.send(mailNotificationInfo);

    }
}
