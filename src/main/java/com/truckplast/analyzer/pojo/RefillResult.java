package com.truckplast.analyzer.pojo;

import com.truckplast.analyzer.dto.FileInfoDto;
import com.truckplast.analyzer.entity.part.PartInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillResult implements Serializable {

    private Set<String> targetPartStorageName;

    private Set<String> currentPartStorageName;

    private List<PartInfo> resultPartInfoList;

    private FileInfoDto fileInfoDto;

}
