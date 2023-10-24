package com.example.mvc.repository;

import com.example.mvc.model.BatteryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatteryRepository extends JpaRepository<BatteryJpaEntity, Long> {
}