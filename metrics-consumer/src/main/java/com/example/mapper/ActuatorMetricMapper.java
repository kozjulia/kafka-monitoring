package com.example.mapper;

import com.example.dto.ActuatorMetricDto;
import com.example.model.ActuatorMetric;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AvailableTagMapper.class, MeasurementMapper.class})
public interface ActuatorMetricMapper {

    ActuatorMetricDto toActuatorMetricDto(ActuatorMetric actuatorMetric);

    List<ActuatorMetricDto> convertActuatorMetricListToActuatorMetricDtoList(List<ActuatorMetric> list);

}