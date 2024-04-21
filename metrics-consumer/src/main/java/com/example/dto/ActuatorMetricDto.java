package com.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "Метрика")
public class ActuatorMetricDto {

    @Schema(description = "Идентификатор", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Наименование метрики", example = "jdbc.connections.active")
    private String name;

    @Schema(description = "Полное описание метрики",
            example = "Current number of active connections that have been allocated from the data source")
    private String description;

    @Schema(description = "Основная единица метрики", example = "threads")
    private String baseUnit;

    private List<MeasurementDto> measurements;

    private List<AvailableTagDto> availableTags;

}