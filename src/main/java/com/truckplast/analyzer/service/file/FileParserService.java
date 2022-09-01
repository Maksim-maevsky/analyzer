package com.truckplast.analyzer.service.file;


import com.truckplast.analyzer.entity.MailInfo;

import java.util.List;

public interface FileParserService {

    void pars(List<MailInfo> mailInfoDtoList);

}
