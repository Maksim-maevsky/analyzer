package com.truckplast.analyzer.service.mail;



import com.truckplast.analyzer.entity.MailInfo;

import java.util.List;

public interface MailCheckerService {

     List<MailInfo> checkEmail();
}
