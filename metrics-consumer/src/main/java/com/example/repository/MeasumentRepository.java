package com.example.repository;

import com.example.model.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasumentRepository extends JpaRepository<Measurement, Long> {

}