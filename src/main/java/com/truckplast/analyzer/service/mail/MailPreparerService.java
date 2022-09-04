package com.truckplast.analyzer.service.mail;


import com.truckplast.analyzer.pojo.MailNotificationInfo;
import com.truckplast.analyzer.pojo.RefillResult;

public interface MailPreparerService {

    MailNotificationInfo refillPartStoragePreparation(RefillResult refillResultDto);
}
