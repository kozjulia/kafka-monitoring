package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

@Schema(name = "Page<ActuatorMetricDto>", description = "Page of ActuatorMetricDto")
public abstract class ActuatorMetricPage implements Page<ActuatorMetricDto> {

}