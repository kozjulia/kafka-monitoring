package com.example.service;

import com.example.model.ActuatorMetric;
import com.example.model.AvailableTag;
import com.example.model.Measurement;
import com.example.repository.ActuatorMetricRepository;
import com.example.repository.AvailableTagRepository;
import com.example.repository.MeasumentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.kafka.annotation.DltHandler;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;
import org.springframework.kafka.annotation.KafkaListener;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaListenerService {

    private final KafkaTemplate<Object, Object> kafkaTemplates;

    private static final String TOPIC_NAME = "${message.topic.name}";
    private static final String TOPIC_NAME_DLT = "${message.topic.name.dlt}";
    private final ObjectMapper objectMapper;
    private final ActuatorMetricRepository metricRepository;
    private final AvailableTagRepository tagRepository;
    private final MeasumentRepository measumentRepository;

    @KafkaListener(id = "metricsConsumerGroup", topics = TOPIC_NAME)
    @RetryableTopic(attempts = "5", backoff = @Backoff(delay = 2000, maxDelay = 10000, multiplier = 2))
    public void consumeMessage(String message) {

        try {
            ActuatorMetric actuatorMetric = objectMapper.readValue(message, ActuatorMetric.class);
            metricRepository.save(actuatorMetric);

            List<AvailableTag> availableTags = actuatorMetric.getAvailableTags().stream().
                    map(tag -> {
                        tag.setMetric(actuatorMetric);
                        return tag;
                    }).toList();
            List<Measurement> measurements = actuatorMetric.getMeasurements().stream().
                    map(measurement -> {
                        measurement.setMetric(actuatorMetric);
                        return measurement;
                    }).toList();
            tagRepository.saveAll(availableTags);
            measumentRepository.saveAll(measurements);

            log.info("Received: {}", actuatorMetric);
        } catch (JsonProcessingException | DataIntegrityViolationException exc) {
            kafkaTemplates.send(TOPIC_NAME_DLT, message);
        }
    }

    @KafkaListener(id = "dltGroup", topics = TOPIC_NAME_DLT)
    public void dltListen(byte[] in) {
        log.info("Received from DLT: {}", new String(in));
    }

    @DltHandler
    public void listenDlt(String in, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Received from DLT: {}, topic: {}, offset: {}", in, topic, offset);
    }

}