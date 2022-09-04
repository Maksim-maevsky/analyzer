package com.truckplast.analyzer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillResponse {

    private PartStorageInfo targetPartStorageInfo;

    private PartStorageInfo currentPartStorageInfo;


}
