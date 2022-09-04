package com.truckplast.analyzer.pojo;


import com.truckplast.analyzer.dto.FileInfoDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailNotificationInfo {

    private String from;

    private String to;

    private String subject;

    private String message;

    private FileInfoDto fileInfoDto;

}
