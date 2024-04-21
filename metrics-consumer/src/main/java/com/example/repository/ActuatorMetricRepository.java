package com.example.repository;

import com.example.model.ActuatorMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActuatorMetricRepository extends JpaRepository<ActuatorMetric, Long> {

}