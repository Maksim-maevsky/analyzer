package com.truckplast.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillResponseDto {

    private PartStorageInfoDto targetPartStorageInfoDto;

    private PartStorageInfoDto currentPartStorageInfoDto;


}
