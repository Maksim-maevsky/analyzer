package com.truckplast.analyzer.mapper;


import com.truckplast.analyzer.dto.PartStorageDto;
import com.truckplast.analyzer.entity.part.PartStorage;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PartStorageMapper {

    @Named(value = "partStorage")
    PartStorageDto toPartStorageDtoFromPartStorage(PartStorage partStorage);

    @Named(value = "partStorageDto")
    PartStorage toPartStorageFromPartStorageDto(PartStorageDto partStorageDto);

    @IterableMapping(qualifiedByName = "partStorage")
    List<PartStorageDto> mapPartStorageDtoListFromPartStorageList(List<PartStorage> partStorageList);

}