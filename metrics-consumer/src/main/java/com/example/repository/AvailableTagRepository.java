package com.example.repository;

import com.example.model.AvailableTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvailableTagRepository extends JpaRepository<AvailableTag, Long> {

}