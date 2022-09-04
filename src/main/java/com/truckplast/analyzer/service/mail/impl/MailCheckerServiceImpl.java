package com.truckplast.analyzer.service.mail.impl;


import com.truckplast.analyzer.exeption_handler.exception.InvalidContentException;
import com.truckplast.analyzer.service.file.FileInfoService;
import com.truckplast.analyzer.service.mail.MailCheckerService;
import com.truckplast.analyzer.util.MailUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.mail.Folder;
import javax.mail.Message;
import java.util.Arrays;
import java.util.List;

@Service
@Data
@Slf4j
@RequiredArgsConstructor
public class MailCheckerServiceImpl implements MailCheckerService {

    private final Folder folder;

    private final FileInfoService fileInfoService;


    @SneakyThrows
    @Override
    public List<Message> checkEmail() {

        log.info("Try to get messages");

        Message[] messages = folder.search(MailUtil.getFlagTerm(folder));
        List<Message> messageList = Arrays.asList(messages);
        if (messageList.isEmpty()) throw new InvalidContentException("There are no new messages.");


        return messageList;
    }

    @Override
    @SneakyThrows
    public void closeFolder() {

        folder.close();

    }
}

