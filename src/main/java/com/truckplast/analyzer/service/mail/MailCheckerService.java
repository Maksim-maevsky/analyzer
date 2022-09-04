package com.truckplast.analyzer.service.mail;



import lombok.SneakyThrows;

import javax.mail.Message;
import java.util.List;

public interface MailCheckerService {

     List<Message> checkEmail();

     @SneakyThrows
     void closeFolder();
}
