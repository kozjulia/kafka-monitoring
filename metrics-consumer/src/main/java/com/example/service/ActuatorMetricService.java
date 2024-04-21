package com.example.service;

import com.example.dto.ActuatorMetricDto;
import org.springframework.data.domain.Page;

public interface ActuatorMetricService {

    ActuatorMetricDto getActuatorMetricById(Long actuatorMetricId);

    Page<ActuatorMetricDto> getAllActuatorMetrics(Integer from, Integer size);

}