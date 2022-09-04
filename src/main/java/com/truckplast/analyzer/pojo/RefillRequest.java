package com.truckplast.analyzer.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefillRequest implements Serializable {

    private Set<String> targetPartStorageNameSet;

    private Set<String> currentPartStorageNameSet;

}
