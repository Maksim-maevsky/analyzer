package com.truckplast.analyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.mail.Multipart;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailInfo {

    private UUID id;

    private String subject;

    private String from;

    private Multipart multipart;

    private List<FileInfo> fileInfoList;

    private LocalDateTime dateTime;

}
