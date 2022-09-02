package com.truckplast.analyzer.dto;


import com.truckplast.analyzer.entity.part.PartInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartStorageInfoDto {

    private Set<String> partStorageNameSet;

    private List<PartInfo> partList;

}
