package com.truckplast.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfoDto {

    private String fileName;

    private String extension;

    private byte[] fileBytes;

}
