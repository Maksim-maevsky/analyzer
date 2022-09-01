package com.truckplast.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillResultDto implements Serializable {

    private Set<String> targetPartStorageName;

    private Set<String> currentPartStorageName;

    private List<PartInfoDto> resultPartInfoDtoList;

    private FileInfoDto fileInfoDto;

}
