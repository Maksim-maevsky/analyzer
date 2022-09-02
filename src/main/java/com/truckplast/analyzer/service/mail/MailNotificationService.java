package com.truckplast.analyzer.service.mail;


import com.truckplast.analyzer.pojo.MailNotificationInfo;

public interface MailNotificationService {

    void send(MailNotificationInfo mailNotificationInfo);
}
