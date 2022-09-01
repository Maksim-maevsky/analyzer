package com.truckplast.analyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private List<FileInfo> fileInfoList;

    private LocalDateTime dateTime;

}
