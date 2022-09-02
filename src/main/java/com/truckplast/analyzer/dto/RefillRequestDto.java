package com.truckplast.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillRequestDto implements Serializable {

    private Set<String> targetPartStorageNameSet;

    private Set<String> currentPartStorageNameSet;

}
