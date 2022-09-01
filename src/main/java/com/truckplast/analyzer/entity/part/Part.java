package com.truckplast.analyzer.entity.part;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Part {

    private UUID id;

    private String code;

    private String description;

    private Brand brand;

    private LocalDateTime createDate;

}
