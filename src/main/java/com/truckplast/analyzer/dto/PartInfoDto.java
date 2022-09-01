package com.truckplast.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartInfoDto {

    private UUID id;

    private int count;

    private short partStorageId;

    private PartDto part;

    private LocalDateTime createDate;

}
