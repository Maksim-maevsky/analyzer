package com.truckplast.analyzer.service.mail;



import com.truckplast.analyzer.entity.MailInfo;

import java.util.List;

public interface MailInfoService {

    void setLocalDateTimeAndId(List<MailInfo> mailInfoList);

    void saveAll(List<MailInfo> mailInfoList);

}
