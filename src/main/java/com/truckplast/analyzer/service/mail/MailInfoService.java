package com.truckplast.analyzer.service.mail;



import com.truckplast.analyzer.entity.MailInfo;

import javax.mail.Message;
import java.util.List;

public interface MailInfoService {

    void saveAll(List<MailInfo> mailInfoList);

    List<MailInfo> getMailInfos(List<Message> messageList);

}
