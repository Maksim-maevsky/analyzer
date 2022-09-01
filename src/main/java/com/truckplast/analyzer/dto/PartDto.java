package com.truckplast.analyzer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartDto {

    private UUID id;

    private String code;

    private String description;

    private BrandDto brand;

    private LocalDateTime createDate;

}
