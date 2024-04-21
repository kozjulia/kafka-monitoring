package com.example.service;

import com.example.feign.ActuatorClient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class KafkaServiceImpl implements KafkaService {

    private final ActuatorClient actuatorClient;
    private final KafkaTemplate<Object, Object> template;

    @Value("${message.topic.name}")
    private String topicName;

    @Override
    public void sendMetrics() {
        List<String> metrics = new ArrayList<>();
        metrics.add(actuatorClient.getMetric("application.ready.time"));
        metrics.add(actuatorClient.getMetric("jdbc.connections.active"));
        metrics.add(actuatorClient.getMetric("jvm.threads.live"));
        metrics.add(actuatorClient.getMetric("jvm.memory.used"));
        metrics.add(actuatorClient.getMetric("process.uptime"));

        metrics.forEach(metric -> {
                    CompletableFuture<SendResult<Object, Object>> future = template.send(topicName, metric);
                    future.whenComplete((result, ex) -> {
                        if (ex == null) {
                            log.info("Sent message=[" + metric + "] with offset=[" +
                                    result.getRecordMetadata().offset() + "]");
                        } else {
                            log.info("Unable to send message=[" + metric + "] due to : " + ex.getMessage());
                        }
                    });
                }
        );
    }

}