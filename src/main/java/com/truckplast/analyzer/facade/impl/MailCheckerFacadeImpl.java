package com.truckplast.analyzer.facade.impl;

import com.truckplast.analyzer.entity.MailInfo;
import com.truckplast.analyzer.facade.MailCheckerFacade;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.file.FileParserService;
import com.truckplast.analyzer.service.mail.MailCheckerService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MailCheckerFacadeImpl implements MailCheckerFacade {

    private final MailCheckerService mailCheckerService;

    private final FileInfoService fileInfoService;

    private final FileParserService fileParserService;


    @Override
    public void checkEmailAndSaveInfo() {

        List<MailInfo> mailList = mailCheckerService.checkEmail();
        fileParserService.pars(mailList);

    }
}
