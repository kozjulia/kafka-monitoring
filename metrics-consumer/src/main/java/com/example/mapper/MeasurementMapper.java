package com.example.mapper;

import com.example.dto.MeasurementDto;
import com.example.model.Measurement;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MeasurementMapper {

    @Mapping(target = "metric", source = "metric", ignore = true)
    MeasurementDto toMeasurementDto(Measurement measurement);

    List<MeasurementDto> convertMeasurementListToMeasurementDtoList(List<Measurement> list);

}