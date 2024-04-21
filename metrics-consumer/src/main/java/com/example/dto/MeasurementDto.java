package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Доступные теги")
public class MeasurementDto {

    @Schema(description = "Идентификатор", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Метрика", example = "1")
    @JsonIgnore
    private ActuatorMetricDto metric;

    @Schema(description = "Статистика", example = "VALUE")
    private String statistic;

    @Schema(description = "Значение статистики", example = "32")
    private Double value;

}