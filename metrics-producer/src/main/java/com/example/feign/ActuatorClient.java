package com.example.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "actuator-service")
public interface ActuatorClient {

    @GetMapping("/actuator/metrics/{requiredMetricName}")
    String getMetric(@PathVariable String requiredMetricName);

}