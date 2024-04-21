package com.example.service;

import com.example.dto.ActuatorMetricDto;
import com.example.exception.NotFoundException;
import com.example.mapper.ActuatorMetricMapper;
import com.example.model.ActuatorMetric;
import com.example.repository.ActuatorMetricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ActuatorMetricServiceImpl implements ActuatorMetricService {

    private final ActuatorMetricRepository metricRepository;
    private final ActuatorMetricMapper metricMapper;

    @Override
    public ActuatorMetricDto getActuatorMetricById(Long actuatorMetricId) {
        return metricMapper.toActuatorMetricDto(returnActuatorMetric(actuatorMetricId));
    }

    @Override
    public Page<ActuatorMetricDto> getAllActuatorMetrics(Integer from, Integer size) {
        Pageable page = PageRequest.of(from, size);
        Page<ActuatorMetric> metricPage = metricRepository.findAll(page);
        return metricPage.map(metricMapper::toActuatorMetricDto);
    }

    private ActuatorMetric returnActuatorMetric(Long actuatorMetricId) {
        return metricRepository.findById(actuatorMetricId)
                .orElseThrow(() -> new NotFoundException("Метрика с id = " + actuatorMetricId + " не найдена."));
    }

}