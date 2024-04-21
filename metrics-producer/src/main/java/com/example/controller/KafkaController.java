package com.example.controller;

import com.example.service.KafkaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Метрики", description = "Взаимодействие с метриками")
public class KafkaController {

    private final KafkaService kafkaService;

    @PostMapping(path = "/metrics")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Scheduled(fixedRate = 10000, initialDelay = 10000)
    @Operation(
            summary = "Отправить метрики (по расписанию)",
            description = "Позволяет отправить метрики по расписанию")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "No content")})
    /**
     * Отправка метрик по расписанию.
     */
    public void sendMetrics() {
        kafkaService.sendMetrics();
    }

}