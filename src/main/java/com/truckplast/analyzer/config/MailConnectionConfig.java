package com.truckplast.analyzer.config;


import com.truckplast.analyzer.constant.MailConstant;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.mail.Folder;
import javax.mail.Session;
import javax.mail.Store;
import java.util.Properties;

@Configuration
public class MailConnectionConfig {

    @Value("${javax.mail.user}")
    private String user;

    @Value("${javax.mail.password}")
    private String password;

    @Value("${javax.mail.host}")
    private String host;

    @Value("${javax.mail.folder}")
    private String folderName;


    @SneakyThrows
    @Bean
    public Folder getFolder() {

        Properties properties = new Properties();
        properties.put(MailConstant.MAIL_PROTOCOL, MailConstant.IMAP_PROTOCOL_TYPE);
        Store store = Session.getInstance(properties).getStore();
        store.connect(host, user, password);

        return store.getFolder(folderName);
    }
}
