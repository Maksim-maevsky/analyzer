package com.truckplast.analyzer.facade.impl;

import com.truckplast.analyzer.entity.FileInfo;
import com.truckplast.analyzer.entity.MailInfo;
import com.truckplast.analyzer.facade.MailCheckerFacade;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.file.FileParserService;
import com.truckplast.analyzer.service.mail.MailCheckerService;
import com.truckplast.analyzer.service.mail.MailInfoService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MailCheckerFacadeImpl implements MailCheckerFacade {

    private final MailCheckerService mailCheckerService;

    private final FileInfoService fileInfoService;

    private final FileParserService fileParserService;

    private final MailInfoService mailInfoService;

    @Override
    public void checkEmailAndSaveInfo() {

        List<Message> messageList = mailCheckerService.checkEmail();
        List<MailInfo> mailInfoList = mailInfoService.getMailInfos(messageList);
        mailInfoService.saveAll(mailInfoList);
        mailInfoList.parallelStream().forEach(mailInfo -> {

            List<FileInfo> fileInfos = fileInfoService.iterateMimeBodyParts(mailInfo.getMultipart());
            fileParserService.parsAndSave(fileInfos, mailInfo.getId());

        });
        mailCheckerService.closeFolder();
    }
}
