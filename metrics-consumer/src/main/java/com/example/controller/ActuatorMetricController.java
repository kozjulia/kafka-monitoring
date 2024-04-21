package com.example.controller;

import com.example.dto.ActuatorMetricDto;
import com.example.dto.ActuatorMetricPage;
import com.example.exception.ApiError;
import com.example.service.ActuatorMetricService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import static com.example.swagger.ResponseExample.*;

@RestController
@RequestMapping("/metrics")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Метрики", description = "Взаимодействие с метриками")
public class ActuatorMetricController {

    private final ActuatorMetricService metricService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Получить метрику",
            description = "Позволяет возвратить информацию о метрике по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActuatorMetricDto.class))}),
            @ApiResponse(responseCode = "404", description = "The required object was not found",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ApiError.class),
                            examples = {@ExampleObject(value = ACTUATOR_METRIC_ERROR_404_EXAMPLE)})})})
    /**
     * Возвращение информации о метрике по id.
     */
    public ResponseEntity<ActuatorMetricDto> getActuatorMetricById(
            @PathVariable @Parameter(description = "Идентификатор метрики", required = true) Long id) {
        ActuatorMetricDto metricDto = metricService.getActuatorMetricById(id);
        return ResponseEntity.ok().body(metricDto);
    }

    @GetMapping
    @Operation(
            summary = "Получить все метрики",
            description = "Позволяет возвратить информацию о всех метриках")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ActuatorMetricPage.class))})})
    /**
     * Возвращение информации о метриках.
     */
    public ResponseEntity<Page<ActuatorMetricDto>> getAllActuatorMetrics(
            @PositiveOrZero @RequestParam(name = "from", defaultValue = "0")
            @Parameter(description = "номер страницы") Integer from,
            @Positive @Max(500) @RequestParam(name = "size", defaultValue = "10")
            @Parameter(description = "размер страницы") Integer size) {
        Page<ActuatorMetricDto> metricDtos = metricService.getAllActuatorMetrics(from, size);
        log.info("Получен список метрик, from = {}, size = {}, количество = {}.", from, size, metricDtos.stream().count());
        return ResponseEntity.ok().body(metricDtos);
    }

}