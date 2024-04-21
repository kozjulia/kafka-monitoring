package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Data;

@Data
@Schema(description = "Доступные теги")
public class AvailableTagDto {

    @Schema(description = "Идентификатор", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @Schema(description = "Метрика", example = "1")
    @JsonIgnore
    private ActuatorMetricDto metric;

    @Schema(description = "Тэг", example = "name")
    private String tag;

    private List<String> values;

}