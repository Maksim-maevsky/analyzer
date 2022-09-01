package com.truckplast.analyzer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {

    private UUID id;

    private String fileName;

    private String extension;

    private byte[] fileBytes;

    private UUID mailInfoId;

}

