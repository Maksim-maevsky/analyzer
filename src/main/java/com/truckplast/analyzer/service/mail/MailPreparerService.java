package com.truckplast.analyzer.service.mail;


import com.truckplast.analyzer.dto.RefillResultDto;
import com.truckplast.analyzer.pojo.MailNotificationInfo;

public interface MailPreparerService {

    MailNotificationInfo refillPartStoragePreparation(RefillResultDto refillResultDto);
}
